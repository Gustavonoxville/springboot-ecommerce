package com.gustavo.ecommerce.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gustavo.ecommerce.dto.OrderItemRequestDTO;
import com.gustavo.ecommerce.dto.OrderRequestDTO;
import com.gustavo.ecommerce.dto.OrderResponseDTO;
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

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

	@InjectMocks
	private OrderService orderService;

	@Mock
	private ProductRepository productRepository;
	@Mock
	private OrderRepository orderRepository;
	@Mock
	private OrderItemRepository orderItemRepository;
	@Mock
	private UserRepository userRepository;

	private User user;
	private Product product;
	private UUID productId;

	@BeforeEach
	void setUp() {
		user = new User();
		user.setId(UUID.randomUUID());
		user.setUsername("gustavo");

		productId = UUID.randomUUID();
		product = new Product();
		product.setId(productId);
		product.setPrice(new BigDecimal("100"));
		product.setStockQuantity(10);
		product.setName("Test Product");
	}

	@Test
	void shouldCreateOrderSuccessfully() {

		OrderItemRequestDTO itemDto = new OrderItemRequestDTO();
		itemDto.setProductId(productId);
		itemDto.setQuantity(2);

		OrderRequestDTO request = new OrderRequestDTO();
		request.setItems(List.of(itemDto));

		when(userRepository.findByUsername("gustavo")).thenReturn(Optional.of(user));
		when(productRepository.findById(productId)).thenReturn(Optional.of(product));
		when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

		OrderResponseDTO response = orderService.createOrder(request, "gustavo");

		assertNotNull(response);
		assertEquals(new BigDecimal("200"), response.getTotal());
		assertEquals(OrderStatus.PENDENTE, response.getStatus());
		verify(orderRepository, times(1)).save(any(Order.class));
		verify(orderItemRepository, times(1)).saveAll(anyList());
	}

	@Test
	void shouldThrowExceptionIfUserNotFound() {

		when(userRepository.findByUsername("gustavo")).thenReturn(Optional.empty());

		OrderRequestDTO request = new OrderRequestDTO();
		request.setItems(new ArrayList<>());

		RuntimeException ex = assertThrows(RuntimeException.class, () -> {
			orderService.createOrder(request, "gustavo");
		});

		assertTrue(ex.getMessage().contains("Usuário não encontrado"));
	}

	@Test
	void shouldThrowExceptionIfProductNotFound() {

		OrderItemRequestDTO itemDto = new OrderItemRequestDTO();
		itemDto.setProductId(productId);
		itemDto.setQuantity(1);

		OrderRequestDTO request = new OrderRequestDTO();
		request.setItems(List.of(itemDto));

		when(userRepository.findByUsername("gustavo")).thenReturn(Optional.of(user));
		when(productRepository.findById(productId)).thenReturn(Optional.empty());

		RuntimeException ex = assertThrows(RuntimeException.class, () -> {
			orderService.createOrder(request, "gustavo");
		});

		assertTrue(ex.getMessage().contains("Produto não encontrado"));
	}

	@Test
	void shouldThrowExceptionIfInsufficientStock() {

		product.setStockQuantity(1);

		OrderItemRequestDTO itemDto = new OrderItemRequestDTO();
		itemDto.setProductId(productId);
		itemDto.setQuantity(5);

		OrderRequestDTO request = new OrderRequestDTO();
		request.setItems(List.of(itemDto));

		when(userRepository.findByUsername("gustavo")).thenReturn(Optional.of(user));
		when(productRepository.findById(productId)).thenReturn(Optional.of(product));

		BusinessException ex = assertThrows(BusinessException.class, () -> {
			orderService.createOrder(request, "gustavo");
		});

		assertTrue(ex.getMessage().contains("Estoque insuficiente para o produto"));
	}

	@Test
	void shouldPayOrderSuccessfully() {

		Order order = new Order();
		order.setId(UUID.randomUUID());
		order.setUser(user);
		order.setStatus(OrderStatus.PENDENTE);
		order.setItems(new ArrayList<>());

		OrderItem item = new OrderItem();
		item.setProduct(product);
		item.setQuantity(2);
		order.getItems().add(item);

		when(userRepository.findByUsername("gustavo")).thenReturn(Optional.of(user));
		when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));

		OrderResponseDTO response = orderService.payOrder(order.getId(), "gustavo");

		assertEquals(OrderStatus.PAGO, response.getStatus());
		verify(orderRepository, times(1)).save(order);
		verify(productRepository, times(1)).save(product);
	}

	@Test
	void shouldCancelOrderIfNoStockOnPayment() {

		product.setStockQuantity(1);

		Order order = new Order();
		order.setId(UUID.randomUUID());
		order.setUser(user);
		order.setStatus(OrderStatus.PENDENTE);

		OrderItem item = new OrderItem();
		item.setProduct(product);
		item.setQuantity(5);
		order.setItems(List.of(item));

		when(userRepository.findByUsername("gustavo")).thenReturn(Optional.of(user));
		when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));

		BusinessException ex = assertThrows(BusinessException.class, () -> {
			orderService.payOrder(order.getId(), "gustavo");
		});

		assertEquals(OrderStatus.CANCELADO, order.getStatus());
		verify(orderRepository, times(1)).save(order);
	}
}