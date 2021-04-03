package com.code.customer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.customer.model.OrderDto;
import com.code.customer.model.Stock;
import com.code.customer.model.CustomerOrder;
import com.code.customer.model.CustomerOrderResponse;
import com.code.customer.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	public static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService cusService;

	@PostMapping("/placeOrder")
	private String placeOrder(@RequestBody OrderDto reqOrderDto) {
		return cusService.placeOrder(reqOrderDto);
	}

	@GetMapping("/viewAllOrderStatus")
	private List<CustomerOrder> viewAllOrderStatus() {
		List<CustomerOrder> orderList = new ArrayList<CustomerOrder>();
		orderList = cusService.viewAllOrderStatus();
		return orderList;

	}

	@GetMapping("/getAllOrdersByStockId")
	private List<CustomerOrderResponse> getAllOrdersByStockId(@RequestParam int stockId) {
		List<CustomerOrderResponse> orderList = new ArrayList<CustomerOrderResponse>();
		orderList = cusService.getAllOrdersByStockId(stockId);
		return orderList;

	}
	@PutMapping("/UpdateCustomerOrder")
	public String UpdateCustomerOrder(@RequestBody CustomerOrderResponse customerOrder){
		cusService.UpdateCustomerOrder(customerOrder);
		return "Success";
	}

}
