package com.gustavo.ecommerce.controller;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.ecommerce.dto.OrderRequestDTO;
import com.gustavo.ecommerce.dto.OrderResponseDTO;
import com.gustavo.ecommerce.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO request, Principal principal) {
		OrderResponseDTO order = orderService.createOrder(request, principal.getName());
		return ResponseEntity.status(HttpStatus.CREATED).body(order);
	}

	@PostMapping("/{orderId}/pay")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<OrderResponseDTO> payOrder(@PathVariable UUID orderId, Principal principal) {
		OrderResponseDTO order = orderService.payOrder(orderId, principal.getName());
		return ResponseEntity.ok(order);
	}

	@GetMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<OrderResponseDTO>> getUserOrders(Principal principal) {
		List<OrderResponseDTO> orders = orderService.getOrdersByUsername(principal.getName());
		return ResponseEntity.ok(orders);
	}
}