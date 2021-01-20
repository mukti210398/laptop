package com.example.laptopium.networkmanager.checkout.network;

import com.example.laptopium.networkmanager.checkout.model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ICheckoutApis {
    @POST("checkout/order")
    Call<Order> sendOrder(@Body Order order);

    @GET("/checkout/order/{userId}")
    Call<List<Order>> confirmOrder(@Path("userId") String userId);

}
