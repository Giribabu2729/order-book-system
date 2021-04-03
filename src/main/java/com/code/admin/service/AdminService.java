package com.code.admin.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.code.admin.dto.CustomerOrderResponse;
import com.code.admin.model.Stock;

public interface AdminService {
	
	List<Stock> viewStockDetails();
	String updateMarketStatus();
	String executeOrder(int stockId,int qty, int price);
	String updateMarketStatusByStockId(int stockId, String status);
	//List<CustomerOrderResponse> viewCustomerOrdersByStockId(@PathVariable int stockId);
}
