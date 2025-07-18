package com.gustavo.ecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name; // "ADMIN", "USER"

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	// Getters e Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}