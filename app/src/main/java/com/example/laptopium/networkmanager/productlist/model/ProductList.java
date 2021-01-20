package com.example.laptopium.networkmanager.productlist.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductList implements Serializable {

	public ProductList(String product, String typeName, String merchantId, float price, String imageUrl, String company, String id) {
		this.product = product;
		this.typeName = typeName;
		this.merchantId = merchantId;
		this.price = price;
		this.imageUrl = imageUrl;
		this.company = company;
		this.id = id;
	}

	@SerializedName("product")
	private String product;

	@SerializedName("memory")
	private String memory;

	@SerializedName("merchantProductRating")
	private double merchantProductRating;

	@SerializedName("typeName")
	private String typeName;

	@SerializedName("weight")
	private String weight;

	@SerializedName("gpu")
	private String gpu;

	@SerializedName("inches")
	private double inches;

	@SerializedName("merchantId")
	private String merchantId;

	@SerializedName("price")
	private float price;

	@SerializedName("imageUrl")
	private String imageUrl;

	@SerializedName("company")
	private String company;

	@SerializedName("id")
	private String id;

	@SerializedName("screenResolution")
	private String screenResolution;

	@SerializedName("opSys")
	private String opSys;

	@SerializedName("ram")
	private String ram;

	public void setProduct(String product){
		this.product = product;
	}

	public String getProduct(){
		return product;
	}

	public void setMemory(String memory){
		this.memory = memory;
	}

	public String getMemory(){
		return memory;
	}

	public void setMerchantProductRating(double merchantProductRating){
		this.merchantProductRating = merchantProductRating;
	}

	public double getMerchantProductRating(){
		return merchantProductRating;
	}

	public void setTypeName(String typeName){
		this.typeName = typeName;
	}

	public String getTypeName(){
		return typeName;
	}

	public void setWeight(String weight){
		this.weight = weight;
	}

	public String getWeight(){
		return weight;
	}

	public void setGpu(String gpu){
		this.gpu = gpu;
	}

	public String getGpu(){
		return gpu;
	}

	public void setInches(double inches){
		this.inches = inches;
	}

	public double getInches(){
		return inches;
	}

	public void setMerchantId(String merchantId){
		this.merchantId = merchantId;
	}

	public String getMerchantId(){
		return merchantId;
	}

	public void setPrice(float price){
		this.price = price;
	}

	public double getPrice(){
		return price;
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

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setScreenResolution(String screenResolution){
		this.screenResolution = screenResolution;
	}

	public String getScreenResolution(){
		return screenResolution;
	}

	public void setOpSys(String opSys){
		this.opSys = opSys;
	}

	public String getOpSys(){
		return opSys;
	}

	public void setRam(String ram){
		this.ram = ram;
	}

	public String getRam(){
		return ram;
	}

	@Override
 	public String toString(){
		return 
			"ProductList{" + 
			"product = '" + product + '\'' + 
			",memory = '" + memory + '\'' + 
			",merchantProductRating = '" + merchantProductRating + '\'' + 
			",typeName = '" + typeName + '\'' + 
			",weight = '" + weight + '\'' + 
			",gpu = '" + gpu + '\'' + 
			",inches = '" + inches + '\'' + 
			",merchantId = '" + merchantId + '\'' + 
			",price = '" + price + '\'' + 
			",imageUrl = '" + imageUrl + '\'' + 
			",company = '" + company + '\'' + 
			",id = '" + id + '\'' + 
			",screenResolution = '" + screenResolution + '\'' + 
			",opSys = '" + opSys + '\'' + 
			",ram = '" + ram + '\'' + 
			"}";
		}
}