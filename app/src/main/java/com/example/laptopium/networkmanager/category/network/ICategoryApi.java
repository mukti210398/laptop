package com.example.laptopium.networkmanager.category.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ICategoryApi {
    @GET("product/company")
    Call<List<String>> getCategoryList();
}
