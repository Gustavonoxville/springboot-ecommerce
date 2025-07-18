package com.gustavo.ecommerce.dto;

import java.util.List;

public class OrderRequestDTO {

	private List<OrderItemRequestDTO> items;

	public OrderRequestDTO() {
		super();
	}

	// Getters e Setters
	public List<OrderItemRequestDTO> getItems() {
		return items;
	}

	public void setItems(List<OrderItemRequestDTO> items) {
		this.items = items;
	}

}
