package com.gustavo.ecommerce.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.ecommerce.dto.TopBuyerDTO;
import com.gustavo.ecommerce.dto.UserAverageDTO;
import com.gustavo.ecommerce.service.OrderService;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

	private final OrderService orderService;

	public AnalyticsController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("/top-buyers")
	public List<TopBuyerDTO> getTopBuyers() {
		return orderService.getTopBuyers();
	}

	@GetMapping("/average-ticket")
	public List<UserAverageDTO> getUserAverageTickets() {
		return orderService.getAverageTicketByUser();
	}

	@GetMapping("/monthly-revenue")
	public BigDecimal getMonthlyRevenue() {
		return orderService.getCurrentMonthRevenue();
	}
}