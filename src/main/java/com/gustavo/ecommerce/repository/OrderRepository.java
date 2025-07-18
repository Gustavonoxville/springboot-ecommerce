package com.gustavo.ecommerce.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gustavo.ecommerce.dto.TopBuyerDTO;
import com.gustavo.ecommerce.dto.UserAverageDTO;
import com.gustavo.ecommerce.entity.Order;
import com.gustavo.ecommerce.entity.User;

public interface OrderRepository extends JpaRepository<Order, UUID> {

	List<Order> findByUser(User user);

	@Query("""
			SELECT new com.gustavo.ecommerce.dto.TopBuyerDTO(o.user.username, SUM(o.total))
			FROM Order o
			GROUP BY o.user.username
			ORDER BY SUM(o.total) DESC
			""")
	List<TopBuyerDTO> findTop5Buyers(Pageable pageable);

	@Query("""
			 SELECT new com.gustavo.ecommerce.dto.UserAverageDTO(o.user.username, CAST(AVG(o.total) AS big_decimal))
			 FROM Order o
			 GROUP BY o.user.username
			""")
	List<UserAverageDTO> getAverageTicketByUser();

	@Query("""
			SELECT COALESCE(SUM(o.total), 0)
			FROM Order o
			WHERE MONTH(o.createdAt) = MONTH(CURRENT_DATE)
			  AND YEAR(o.createdAt) = YEAR(CURRENT_DATE)
			""")
	BigDecimal getCurrentMonthRevenue();
}