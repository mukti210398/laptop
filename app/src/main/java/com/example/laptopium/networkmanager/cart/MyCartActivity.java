package com.example.laptopium.networkmanager.cart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laptopium.R;
import com.example.laptopium.networkmanager.authentication.LoginActivity;
import com.example.laptopium.networkmanager.cart.adapter.CartRVAdapter;
import com.example.laptopium.networkmanager.cart.model.CartDetails;
import com.example.laptopium.networkmanager.cart.network.ICartApi;
import com.example.laptopium.networkmanager.cart.retrofitbuilder.RetrofitBuilder;
import com.example.laptopium.networkmanager.checkout.FinalCheckoutActivity;
import com.example.laptopium.networkmanager.productdetails.ProductDetailsActivity;
import com.example.laptopium.networkmanager.productdetails.model.Cart;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyCartActivity extends AppCompatActivity implements CartRVAdapter.ICart {

    CartRVAdapter recyclerViewAdapter;
    List<CartDetails> cartDetailsList = new ArrayList<>();
    private double totalPrice;
    private List<Cart> cartList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        setCart();
        findViewById(R.id.btn_cart_proceed_to_buy).setOnClickListener(view ->
        {
            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if (sharedPreferences.getString("isLoggedIn", "false").equals("false")) {
//                        totalPrice += cartDetails.getPrice() * cartDetails.getQuantity();
//                        TextView tv_total_amount = findViewById(R.id.tv_cart_total_price);
//                        tv_total_amount.setText(String.valueOf(totalPrice));
//                    RecyclerView recyclerView = findViewById(R.id.rv_cart_list);
//                    recyclerViewAdapter = new CartRVAdapter(cartDetailsList, MyCartActivity.this);
//                    recyclerView.setLayoutManager(new LinearLayoutManager(MyCartActivity.this));
//                    recyclerView.setAdapter(recyclerViewAdapter);
                Intent intent = new Intent(MyCartActivity.this, LoginActivity.class);
                Toast.makeText(this, "Login to continue.", Toast.LENGTH_SHORT).show();
                startActivity(intent);


            }else{
                Intent intent = new Intent(MyCartActivity.this, FinalCheckoutActivity.class);
                //pass total price
                intent.putExtra("price", totalPrice);
                startActivity(intent);
            }
        });
    }
    private void setCart() {

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        Retrofit retrofit = RetrofitBuilder.getInstance();
        ICartApi iCartApi = retrofit.create(ICartApi.class);
        String userId="";
        if (sharedPreferences.getString("userMail", null)!=null) {
            String userMail = sharedPreferences.getString("userMail", null);
            int index = userMail.indexOf("@");
            userId = userMail.substring(0, index);
        }
        else if(sharedPreferences.getString("guest", null)!=null){
            userId = sharedPreferences.getString("guest",null);
        }
        if(!userId.equals("")) {
            Call<List<CartDetails>> cartDetails = iCartApi.getCartDetails(userId);
            Callback<List<CartDetails>> callback = new Callback<List<CartDetails>>() {
                @Override
                public void onResponse(Call<List<CartDetails>> call, Response<List<CartDetails>> response) {
                    for (CartDetails cartDetails : response.body()) {
                        cartDetailsList.add(cartDetails);
                        totalPrice += cartDetails.getPrice() * cartDetails.getQuantity();
                        TextView tv_total_amount = findViewById(R.id.tv_cart_total_price);
                        tv_total_amount.setText(String.valueOf(totalPrice));
                    }
                    RecyclerView recyclerView = findViewById(R.id.rv_cart_list);
                    recyclerViewAdapter = new CartRVAdapter(cartDetailsList, MyCartActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MyCartActivity.this));
                    recyclerView.setAdapter(recyclerViewAdapter);
                }

                @Override
                public void onFailure(Call<List<CartDetails>> call, Throwable t) {
                    Toast.makeText(MyCartActivity.this, "Cannot load the cart.", Toast.LENGTH_SHORT).show();
                }
            };
            cartDetails.enqueue(callback);
        }

    }


    @Override
    public void onCartClicked(CartDetails cartDetails) {
        Intent intent = new Intent(MyCartActivity.this, ProductDetailsActivity.class);
        intent.putExtra("productId", cartDetails.getProductId());
        startActivity(intent);
    }

    @Override
    public void load() {
        Intent intent = new Intent(MyCartActivity.this,MyCartActivity.class);
        startActivity(intent);
    }

}
