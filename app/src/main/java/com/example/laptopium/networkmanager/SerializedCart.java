package com.example.laptopium.networkmanager;

import com.example.laptopium.networkmanager.productdetails.model.Cart;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SerializedCart implements Serializable {

    @SerializedName("cartList")
    private List<Cart> cartList = new ArrayList<>();

    public void add(Cart cart)
    {
        cartList.add(cart);
    }
}
