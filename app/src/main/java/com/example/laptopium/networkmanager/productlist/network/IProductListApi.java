package com.example.laptopium.networkmanager.productlist.network;

import com.example.laptopium.networkmanager.productlist.model.ProductList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IProductListApi {
    @GET("product/company/{company}")
    Call<List<ProductList>> getProductList(@Path("company") String company);
}
