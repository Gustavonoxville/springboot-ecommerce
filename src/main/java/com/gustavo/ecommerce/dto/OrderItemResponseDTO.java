package com.gustavo.ecommerce.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.gustavo.ecommerce.entity.OrderItem;

public class OrderItemResponseDTO {

	private UUID productId;
	private String productName;
	private BigDecimal productPrice;
	private Integer quantity;
	private BigDecimal totalPrice;

	public OrderItemResponseDTO() {
	}

	public OrderItemResponseDTO(OrderItem item) {
		this.productId = item.getProduct().getId();
		this.productName = item.getProduct().getName();
		this.productPrice = item.getProduct().getPrice();
		this.quantity = item.getQuantity();
		this.totalPrice = item.getTotalPrice();
	}

	// Getters e Setters
	public UUID getProductId() {
		return productId;
	}

	public void setProductId(UUID productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

}
