package com.gustavo.ecommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.gustavo.ecommerce.entity.Order;
import com.gustavo.ecommerce.entity.enums.OrderStatus;

public class OrderResponseDTO {

	private UUID orderId;
	private BigDecimal total;
	private OrderStatus status;
	private LocalDateTime createdAt;
	private List<OrderItemResponseDTO> items;

	public OrderResponseDTO(Order order) {
		this.orderId = order.getId();
		this.total = order.getTotal();
		this.status = order.getStatus();
		this.createdAt = order.getCreatedAt();
		this.items = order.getItems().stream().map(OrderItemResponseDTO::new).collect(Collectors.toList());
	}

	// Getters e Setters

	public UUID getOrderId() {
		return orderId;
	}

	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<OrderItemResponseDTO> getItems() {
		return items;
	}

	public void setItems(List<OrderItemResponseDTO> items) {
		this.items = items;
	}

}
