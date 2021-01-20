package com.example.laptopium.networkmanager.cart.network;

import com.example.laptopium.networkmanager.cart.model.CartDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ICartApi {
    @GET("checkout/cart/{userId}")
    Call<List<CartDetails>> getCartDetails(@Path("userId") String userId);

    @POST("checkout/cartdelete/")
    Call<List<CartDetails>> deleteProduct(@Body CartDetails cart);

    @POST("checkout/cart/")
    Call<List<CartDetails>> updateProduct(@Body CartDetails cartDetails);

    @GET("checkout/getcartwithqty/{userId}")
    Call<List<CartDetails>> getCart(@Path("userId") String userId);

}
