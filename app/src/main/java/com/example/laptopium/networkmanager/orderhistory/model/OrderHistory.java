package com.example.laptopium.networkmanager.orderhistory.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderHistory implements Serializable {

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("productId")
	private String productId;

	@SerializedName("orderId")
	private int orderId;

	@SerializedName("merchantId")
	private String merchantId;

	@SerializedName("price")
	private double price;

	@SerializedName("orderTimeStamp")
	private String orderTimeStamp;

	@SerializedName("rating")
	private double rating;

	@SerializedName("userId")
	private String userId;

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setOrderId(int orderId){
		this.orderId = orderId;
	}

	public int getOrderId(){
		return orderId;
	}

	public void setMerchantId(String merchantId){
		this.merchantId = merchantId;
	}

	public String getMerchantId(){
		return merchantId;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public double getPrice(){
		return price;
	}

	public void setOrderTimeStamp(String orderTimeStamp){
		this.orderTimeStamp = orderTimeStamp;
	}

	public String getOrderTimeStamp(){
		return orderTimeStamp;
	}

	public void setRating(double rating){
		this.rating = rating;
	}

	public double getRating(){
		return rating;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"OrderHistory{" + 
			"quantity = '" + quantity + '\'' + 
			",productId = '" + productId + '\'' + 
			",orderId = '" + orderId + '\'' + 
			",merchantId = '" + merchantId + '\'' + 
			",price = '" + price + '\'' + 
			",orderTimeStamp = '" + orderTimeStamp + '\'' + 
			",rating = '" + rating + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}