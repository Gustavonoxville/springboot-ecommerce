package com.gustavo.ecommerce.dto;

import java.util.UUID;

public class OrderItemRequestDTO {

	private UUID productId;
	private int quantity;

	public OrderItemRequestDTO() {
		super();
	}

	// Getters e Setters
	public UUID getProductId() {
		return productId;
	}

	public void setProductId(UUID productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
