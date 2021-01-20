package com.example.laptopium.networkmanager.landingpage.network;

import com.example.laptopium.networkmanager.landingpage.model.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ILandingPage {
    @GET("/product/findpopular")
    Call<List<Products>> getAllProducts();

//    @POST(value = "/checkout/order/rating")
//    boolean giveRating(@Body OrderRating orderRating);
}

