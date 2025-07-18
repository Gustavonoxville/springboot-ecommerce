package com.gustavo.ecommerce.entity;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@ManyToOne(optional = false)
	private Product product;

	@ManyToOne(optional = false)
	private Order order;

	private int quantity;

	private BigDecimal unitPrice;

	private BigDecimal totalPrice;

	public OrderItem() {
		super();
	}

	public OrderItem(UUID id, Product product, Order order, int quantity, BigDecimal unitPrice) {
		super();
		this.id = id;
		this.product = product;
		this.order = order;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	// Getters e Setters
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

}