package com.gustavo.ecommerce.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
