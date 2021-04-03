

package com.code.admin.feignclient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.code.admin.dto.CustomerOrderResponse;

@FeignClient(name ="customer-service",  url = "http://localhost:8089/customer")
public interface CustomerOrderClient {

		
	@GetMapping("/getAllOrdersByStockId")
	public List<CustomerOrderResponse> getAllOrdersByStockId(@RequestParam int stockId);
	
	@PutMapping("/UpdateCustomerOrder")
	public String UpdateCustomerOrder(@RequestBody CustomerOrderResponse customerOrder);
	
}

