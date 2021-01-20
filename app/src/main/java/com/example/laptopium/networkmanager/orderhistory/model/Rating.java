package com.example.laptopium.networkmanager.orderhistory.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rating implements Serializable {
    @SerializedName("orderId")
    int orderId;
    @SerializedName("rating")
    double rating;

    public Rating() {
    }

    public Rating(int orderId, double rating) {
        this.orderId = orderId;
        this.rating = rating;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
