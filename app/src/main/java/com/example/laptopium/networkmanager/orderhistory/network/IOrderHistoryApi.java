package com.example.laptopium.networkmanager.orderhistory.network;

import com.example.laptopium.networkmanager.orderhistory.model.OrderHistory;
import com.example.laptopium.networkmanager.orderhistory.model.Rating;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IOrderHistoryApi {
    @GET("/checkout/user/order/{userId}")
    Call<List<OrderHistory>> getOrderHistory(@Path("userId") String userId);

    @POST("/checkout/order/rating")
    Call<Boolean> sendRating(@Body Rating rating);
}
