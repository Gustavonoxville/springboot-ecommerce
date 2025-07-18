package com.gustavo.ecommerce.dto;

import java.math.BigDecimal;

public class TopBuyerDTO {
	private String username;
	private BigDecimal total;

	public TopBuyerDTO() {
		super();
	}

	public TopBuyerDTO(String username, BigDecimal total) {
		this.username = username;
		this.total = total;
	}

	public String getUsername() {
		return username;
	}

	public BigDecimal getTotal() {
		return total;
	}
}