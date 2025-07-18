package com.gustavo.ecommerce.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.ecommerce.entity.Product;
import com.gustavo.ecommerce.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product create(Product product) {
		return productRepository.save(product);
	}

	public Product update(UUID id, Product updated) {
		Product existing = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));

		existing.setName(updated.getName());
		existing.setDescription(updated.getDescription());
		existing.setPrice(updated.getPrice());
		existing.setCategory(updated.getCategory());
		existing.setStockQuantity(updated.getStockQuantity());

		return productRepository.save(existing);
	}

	public void delete(UUID id) {
		productRepository.deleteById(id);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Product findById(UUID id) {
		return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
	}
}