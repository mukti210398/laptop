package com.example.laptopium.networkmanager.checkout.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class MyCart implements Serializable {
    @SerializedName("userId")
    private String userId;
    @SerializedName("productId")
    private String productId;
    @SerializedName("merchantId")
    private String merchantId;
    @SerializedName("quantity")
    private int quantity;

    public MyCart() {
    }

    public MyCart(String userId, String productId, String merchantId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.merchantId = merchantId;
        this.quantity = quantity;
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

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}


