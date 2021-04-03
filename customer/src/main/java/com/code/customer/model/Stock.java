package com.code.customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_id")
	int stockId;
	@Column(name = "product_name")
	String productName;
	@Column(name = "quantity")
	int quantity;
	@Column(name = "market_price")
	int marketPrice; 
	@Column(name = "market_status")
	String marketStatus;
	
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(int marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getMarketStatus() {
		return marketStatus;
	}
	public void setMarketStatus(String marketStatus) {
		this.marketStatus = marketStatus;
	}
	

}
