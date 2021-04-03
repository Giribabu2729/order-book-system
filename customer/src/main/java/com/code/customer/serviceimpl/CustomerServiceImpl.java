package com.code.customer.serviceimpl;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.code.customer.model.CustomerOrder;
import com.code.customer.model.CustomerOrderResponse;
import com.code.customer.model.OrderDto;
import com.code.customer.model.Stock;
import com.code.customer.repository.CustomerOrderRepository;
import com.code.customer.repository.CustomerRepository;
import com.code.customer.repository.StockRepository;
import com.code.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository cusRepo;
	@Autowired
	CustomerOrderRepository cusOrderRepo;
	@Autowired
	StockRepository stockRepo;

	@Override
	public String placeOrder(OrderDto reqOrderDto) {
		String status = "";
		Stock stock = stockRepo.findByStockId(reqOrderDto.getStockId());
		if (stock.getMarketStatus().equalsIgnoreCase("open")) {
			CustomerOrder cusOrder = new CustomerOrder();
			BeanUtils.copyProperties(reqOrderDto, cusOrder);
			cusOrderRepo.save(cusOrder);
			status = "Order Placed Successfully";
		} else
			status = "Market is Closed. You Can't Place Order";

		return status;
	}

	@Override
	public List<CustomerOrder> viewAllOrderStatus() {
		List<CustomerOrder> orderList = new ArrayList<CustomerOrder>();
		orderList = cusOrderRepo.findAll();
		return orderList;
	}
	@Override
	public List<CustomerOrderResponse> getAllOrdersByStockId(@RequestParam int stockId)
	{
		List<CustomerOrderResponse> ordersList=new ArrayList<>();
		List<CustomerOrder> ordersLst=new ArrayList<>();
		ordersLst=cusOrderRepo.getAllOrdersByStockId(stockId);
		for(CustomerOrder order : ordersLst) {
		CustomerOrderResponse order1=new CustomerOrderResponse();
		BeanUtils.copyProperties(order, order1);
		ordersList.add(order1);
		}
		return ordersList;
	}
	@Override
	public void UpdateCustomerOrder(@RequestBody CustomerOrderResponse customerOrder) {
		CustomerOrder custOrder=new CustomerOrder();
		BeanUtils.copyProperties(customerOrder, custOrder);
		cusOrderRepo.save(custOrder);
	}
}
