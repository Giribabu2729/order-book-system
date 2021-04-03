package com.code.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.code.customer.model.CustomerOrder;

import feign.Param;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer>{
	@Query(value = "select * from customer_order where stock_id=:stockId", nativeQuery = true)
	List<CustomerOrder> getAllOrdersByStockId(@Param("stockId") int stockId);

}
