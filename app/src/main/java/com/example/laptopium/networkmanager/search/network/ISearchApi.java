package com.example.laptopium.networkmanager.search.network;

import com.example.laptopium.networkmanager.search.model.SearchProduct;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ISearchApi {
    @GET("search/random/{string}")
    Call<List<SearchProduct>> getSearch(@Path("string") String string);
}
