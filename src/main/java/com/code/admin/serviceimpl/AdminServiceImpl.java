package com.code.admin.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.admin.service.AdminService;
import com.code.admin.dto.CustomerOrderResponse;
import com.code.admin.feignclient.CustomerOrderClient;
import com.code.admin.model.Stock;
import com.code.admin.repository.StockRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	StockRepository stockRepo;
	
	@Autowired
	private CustomerOrderClient custOrderFeign;
	
	@Override
	public List<Stock> viewStockDetails() {
		List<Stock> inventoryList = new ArrayList<>();
		inventoryList = stockRepo.findAll();
		return inventoryList;
	}

	@Override
	public String updateMarketStatus() {
		List<Stock> stockLst = stockRepo.findAll();
		for (Stock stock : stockLst) {
			if (stock.getQuantity() == 0) {
				stock.setMarketStatus("close");
				stockRepo.save(stock);
			} else {
				stock.setMarketStatus("open");
				stockRepo.save(stock);
			}
		}

		return "success";
	}
	
	@Override
	public String updateMarketStatusByStockId(int stockId, String status) {
		Stock stock=stockRepo.findByStockId(stockId);
		stock.setMarketStatus(status);
		stockRepo.save(stock);
		return "Market Status Updated Successfully";
	}

	@Override
	public String executeOrder(int stockId, int qty, int price) {
		List<CustomerOrderResponse> orderList = new ArrayList<CustomerOrderResponse>();
		List<CustomerOrderResponse> placedOrderList = new ArrayList<CustomerOrderResponse>();
		float totalOrderQty = 0;
		orderList = custOrderFeign.getAllOrdersByStockId(stockId);
		for (CustomerOrderResponse order : orderList) {
			if (order.getOrderType().equalsIgnoreCase("Limit") && order.getPrice() >= price
					|| order.getOrderType().equalsIgnoreCase("Market")) {
				totalOrderQty = totalOrderQty + order.getQuantity();
				placedOrderList.add(order);

			} else {
				order.setStatus("Rejected");
				custOrderFeign.UpdateCustomerOrder(order);

			}
		}
		for (CustomerOrderResponse porder : placedOrderList) {
            float orderPer=(qty/totalOrderQty)*100;
			//float orderPer = (sum) * 100;
			int placeQty = (int) (porder.getQuantity() * (orderPer / 100));
			porder.setStatus("accepted");
			porder.setAcceptedQty(placeQty);
			custOrderFeign.UpdateCustomerOrder(porder);
		}
		return "success";

	}

	/*
	 * @Override public List<CustomerOrderResponse> viewCustomerOrdersByStockId(int
	 * stockId) { // TODO Auto-generated method stub return null; }
	 */


}
