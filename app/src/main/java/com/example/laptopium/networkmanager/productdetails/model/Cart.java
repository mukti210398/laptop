package com.example.laptopium.networkmanager.productdetails.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cart implements Serializable {
    @SerializedName("productImageUrl")
    private String productImageUrl;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("productId")
    private String productId;

    @SerializedName("merchantId")
    private String merchantId;

    @SerializedName("price")
    private double price;

    @SerializedName("userId")
    private String userId;

    @SerializedName("productName")
    private String productName;

    public void setProductImageUrl(String productImageUrl){
        this.productImageUrl = productImageUrl;
    }

    public String getProductImageUrl(){
        return productImageUrl;
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

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return userId;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public String getProductName(){
        return productName;
    }

    @Override
    public String toString(){
        return
                "AddProduct{" +
                        "productImageUrl = '" + productImageUrl + '\'' +
                        ",quantity = '" + quantity + '\'' +
                        ",productId = '" + productId + '\'' +
                        ",merchantId = '" + merchantId + '\'' +
                        ",price = '" + price + '\'' +
                        ",userId = '" + userId + '\'' +
                        ",productName = '" + productName + '\'' +
                        "}";
    }
}
