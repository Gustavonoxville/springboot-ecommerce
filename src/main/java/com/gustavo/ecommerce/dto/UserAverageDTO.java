package com.gustavo.ecommerce.dto;

import java.math.BigDecimal;

public class UserAverageDTO {
	private String username;
	private BigDecimal average;

	public UserAverageDTO() {
		super();
	}

	public UserAverageDTO(String username, BigDecimal average) {
		this.username = username;
		this.average = average;
	}

	public String getUsername() {
		return username;
	}

	public BigDecimal getAverage() {
		return average;
	}
}
