package com.example.laptopium.networkmanager.checkout.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Order implements Serializable {

    @SerializedName("orderId")
    int orderId;
    @SerializedName("userId")
    String userId;
    @SerializedName("productId")
    String productId;
    @SerializedName("merchantId")
    String merchantId;
    @SerializedName("quantity")
    int quantity;
    @SerializedName("price")
    float price;
    @SerializedName("orderTimeStamp")
    String orderTimeStamp;
    @SerializedName("rating")
    double rating;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderTimeStamp() {
        return orderTimeStamp;
    }

    public void setOrderTimeStamp(String orderTimeStamp) {
        this.orderTimeStamp = orderTimeStamp;
    }
}



