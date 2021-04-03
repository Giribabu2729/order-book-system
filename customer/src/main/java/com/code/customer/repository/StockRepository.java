package com.code.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.code.customer.model.Stock;

import feign.Param;

public interface StockRepository extends JpaRepository<Stock, Integer> {
	
	Stock findByStockId(int stockId);

	@Query(value = "update stock set market_status=:marketStatus where quantity=0", nativeQuery = true)
	void updateMarketStatus(@Param("marketStatus") String marketStatus);
	
}
