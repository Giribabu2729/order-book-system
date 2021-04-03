package com.code.customer.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.code.customer.model.CustomerOrder;
import com.code.customer.model.CustomerOrderResponse;
import com.code.customer.model.OrderDto;


public interface CustomerService {

	String placeOrder(OrderDto reqOrderDto);
	List<CustomerOrder> viewAllOrderStatus();

	/*
	 * List<Stock> viewStockDetails(); String updateMarketStatus(); String
	 * executeOrder(int stockId,int qty, int price);
	 */
	//String updateMarketStatusByStockId(int stockId, String status);
	List<CustomerOrderResponse> getAllOrdersByStockId(@RequestParam int stockId);
	void UpdateCustomerOrder(@RequestBody CustomerOrderResponse customerOrder);

}
