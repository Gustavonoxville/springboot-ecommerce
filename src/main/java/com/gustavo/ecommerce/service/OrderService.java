package com.gustavo.ecommerce.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gustavo.ecommerce.dto.OrderItemRequestDTO;
import com.gustavo.ecommerce.dto.OrderRequestDTO;
import com.gustavo.ecommerce.dto.OrderResponseDTO;
import com.gustavo.ecommerce.dto.TopBuyerDTO;
import com.gustavo.ecommerce.dto.UserAverageDTO;
import com.gustavo.ecommerce.entity.Order;
import com.gustavo.ecommerce.entity.OrderItem;
import com.gustavo.ecommerce.entity.Product;
import com.gustavo.ecommerce.entity.User;
import com.gustavo.ecommerce.entity.enums.OrderStatus;
import com.gustavo.ecommerce.exception.BusinessException;
import com.gustavo.ecommerce.repository.OrderItemRepository;
import com.gustavo.ecommerce.repository.OrderRepository;
import com.gustavo.ecommerce.repository.ProductRepository;
import com.gustavo.ecommerce.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private UserRepository userRepository;

	public OrderResponseDTO createOrder(OrderRequestDTO dto, String username) {

		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		Order order = new Order();
		order.setUser(user);
		order.setStatus(OrderStatus.PENDENTE);
		order.setCreatedAt(LocalDateTime.now());

		List<OrderItem> orderItems = new ArrayList<>();
		BigDecimal total = BigDecimal.ZERO;

		for (OrderItemRequestDTO itemDto : dto.getItems()) {
			Product product = productRepository.findById(itemDto.getProductId())
					.orElseThrow(() -> new RuntimeException("Produto não encontrado: " + itemDto.getProductId()));

			if (product.getStockQuantity() < itemDto.getQuantity()) {
				throw new BusinessException("Estoque insuficiente para o produto: " + product.getName());
			}

			OrderItem item = new OrderItem();
			item.setProduct(product);
			item.setQuantity(itemDto.getQuantity());
			item.setOrder(order);

			BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity()));
			item.setTotalPrice(itemTotal);

			orderItems.add(item);
			total = total.add(itemTotal);
		}

		order.setTotal(total);
		order.setItems(orderItems);

		orderRepository.save(order);
		orderItemRepository.saveAll(orderItems);

		return new OrderResponseDTO(order);
	}

	public List<OrderResponseDTO> getOrdersByUsername(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new BusinessException("Usuário não encontrado"));

		List<Order> orders = orderRepository.findByUser(user);

		return orders.stream().map(OrderResponseDTO::new).collect(Collectors.toList());
	}

	@Transactional
	public OrderResponseDTO payOrder(UUID orderId, String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new BusinessException("Usuário não encontrado"));

		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new BusinessException("Pedido não encontrado"));

		if (!order.getUser().equals(user)) {
			throw new BusinessException("Você não tem permissão para pagar este pedido.");
		}

		if (!order.getStatus().equals(OrderStatus.PENDENTE)) {
			throw new BusinessException("Este pedido já foi pago ou cancelado.");
		}

		for (OrderItem item : order.getItems()) {
			Product product = item.getProduct();
			int quantity = item.getQuantity();

			if (product.getStockQuantity() < quantity) {
				order.setStatus(OrderStatus.CANCELADO);
				orderRepository.save(order);
				throw new BusinessException("Estoque insuficiente para o produto: " + product.getName());
			}
		}

		// Desconta o estoque
		for (OrderItem item : order.getItems()) {
			Product product = item.getProduct();
			product.setStockQuantity(product.getStockQuantity() - item.getQuantity());
			productRepository.save(product);
		}

		order.setStatus(OrderStatus.PAGO);
		order.setUpdatedAt(LocalDateTime.now());
		orderRepository.save(order);

		return new OrderResponseDTO(order);
	}

	public List<TopBuyerDTO> getTopBuyers() {
		return orderRepository.findTop5Buyers(PageRequest.of(0, 5));
	}

	public List<UserAverageDTO> getAverageTicketByUser() {
		return orderRepository.getAverageTicketByUser();
	}

	public BigDecimal getCurrentMonthRevenue() {
		return orderRepository.getCurrentMonthRevenue();
	}

}