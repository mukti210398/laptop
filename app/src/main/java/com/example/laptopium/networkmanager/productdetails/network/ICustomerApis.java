package com.example.laptopium.networkmanager.productdetails.network;


import com.example.laptopium.networkmanager.productdetails.model.Cart;
import com.example.laptopium.networkmanager.productdetails.model.MerchantProduct;
import com.example.laptopium.networkmanager.productdetails.model.ProductDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ICustomerApis {
    @GET("product/{productId}")
    Call<ProductDetails> getProductDetails(@Path("productId") String productId);

    @GET(value = "merchant/merchantsofproduct/{id}")
    Call<List<MerchantProduct>> findMerchantsOfProduct(@Path ("id") String id);

    @POST("checkout/cart")
    Call<Cart> sendCartDetails(@Body Cart cart);


}
