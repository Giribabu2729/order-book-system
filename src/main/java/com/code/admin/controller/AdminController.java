package com.code.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.admin.dto.CustomerOrderResponse;
import com.code.admin.feignclient.CustomerOrderClient;
import com.code.admin.model.Stock;
import com.code.admin.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@Autowired
	CustomerOrderClient cusOrderFeign;
	
	@GetMapping("/viewAllStocks")
	private List<Stock> viewAllStocks() {
		List<Stock> orderList = new ArrayList<Stock>();
		orderList = adminService.viewStockDetails();
		return orderList;

	}
	@PutMapping("/updateMarketStatus")
	private String updateMarketStatus() {
		return adminService.updateMarketStatus();
	}
	@PutMapping("/updateMarketStatus/{stockId}/{status}")
	private String updateMarketStatus(@PathVariable int stockId,@PathVariable String status) {
		return adminService.updateMarketStatusByStockId(stockId,status);
	}
	
	@PostMapping("/executeOrder")
	private String executeOrder(@RequestParam int stockId,@RequestParam int qty,@RequestParam int price) {
		return adminService.executeOrder(stockId,qty,price);
	}
	
	@GetMapping("/viewCustomerOrdersByStockId/{stockId}")
	private List<CustomerOrderResponse> viewCustomerOrdersByStockId(@PathVariable int stockId) {
	List<CustomerOrderResponse> orderList = new ArrayList<CustomerOrderResponse>();
	orderList = cusOrderFeign.getAllOrdersByStockId(stockId);
	return orderList;

	}

}
