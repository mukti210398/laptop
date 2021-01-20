package com.example.laptopium.networkmanager.productdetails.model;

import com.google.gson.annotations.SerializedName;

public class MerchantProduct{

	@SerializedName("sold")
	private int sold;

	@SerializedName("product")
	private String product;

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("productId")
	private String productId;

	@SerializedName("merchantId")
	private String merchantId;

	@SerializedName("price")
	private double price;

	@SerializedName("merchantProductRating")
	private double merchantProductRating;

	@SerializedName("imageUrl")
	private String imageUrl;

	@SerializedName("company")
	private String company;

	@SerializedName("merchantRating")
	private double merchantRating;

	@SerializedName("merchantName")
	private String merchantName;

	public void setSold(int sold){
		this.sold = sold;
	}

	public int getSold(){
		return sold;
	}

	public void setProduct(String product){
		this.product = product;
	}

	public String getProduct(){
		return product;
	}

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

	public void setMerchantProductRating(double merchantProductRating){
		this.merchantProductRating = merchantProductRating;
	}

	public double getMerchantProductRating(){
		return merchantProductRating;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setCompany(String company){
		this.company = company;
	}

	public String getCompany(){
		return company;
	}

	public void setMerchantRating(double merchantRating){
		this.merchantRating = merchantRating;
	}

	public double getMerchantRating(){
		return merchantRating;
	}

	public void setMerchantName(String merchantName){
		this.merchantName = merchantName;
	}

	public String getMerchantName(){
		return merchantName;
	}

	@Override
 	public String toString(){
		return 
			"MerchantProduct{" + 
			"sold = '" + sold + '\'' + 
			",product = '" + product + '\'' + 
			",quantity = '" + quantity + '\'' + 
			",productId = '" + productId + '\'' + 
			",merchantId = '" + merchantId + '\'' + 
			",price = '" + price + '\'' + 
			",merchantProductRating = '" + merchantProductRating + '\'' + 
			",imageUrl = '" + imageUrl + '\'' + 
			",company = '" + company + '\'' + 
			",merchantRating = '" + merchantRating + '\'' + 
			",merchantName = '" + merchantName + '\'' + 
			"}";
		}
}