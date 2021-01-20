package com.example.laptopium.networkmanager.authentication.network;

import com.example.laptopium.networkmanager.authentication.model.ChangePassword;
import com.example.laptopium.networkmanager.authentication.model.LoginUser;
import com.example.laptopium.networkmanager.authentication.model.RegisterUser;
import com.example.laptopium.networkmanager.authentication.model.UpdateUserId;
import com.example.laptopium.networkmanager.authentication.model.UserDetails;
import com.example.laptopium.networkmanager.authentication.model.UserRegistered;
import com.example.laptopium.networkmanager.cart.model.CartDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IAuthentication {
    @POST("auth/register/user")
    Call<UserRegistered> sendRegisterDetails(@Body RegisterUser registerUser);

    @POST("auth/login/user")
    Call<UserRegistered> sendLoginDetails(@Body LoginUser loginUser);

    @POST("auth/user/changepassword")
    Call<Boolean> sendChangedPassword(@Body ChangePassword changePassword);

    @GET("auth/user/mail/{mailId}")
    Call<UserDetails> getUserDetails(@Path("mailId") String mailId);

    @POST("/checkout/updateuserid")
    Call<List<CartDetails>> updateUserId(@Body UpdateUserId updateUserId);
}
