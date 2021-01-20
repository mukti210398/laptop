package com.example.laptopium.networkmanager.landingpage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laptopium.R;
import com.example.laptopium.networkmanager.authentication.LoginActivity;
import com.example.laptopium.networkmanager.authentication.ProfileActivity;
import com.example.laptopium.networkmanager.cart.MyCartActivity;
import com.example.laptopium.networkmanager.category.CategoryActivity;
import com.example.laptopium.networkmanager.landingpage.adapter.LandingPageRVAdapter;
import com.example.laptopium.networkmanager.landingpage.model.Products;
import com.example.laptopium.networkmanager.landingpage.network.ILandingPage;
import com.example.laptopium.networkmanager.landingpage.retrofitbuilder.RetrofitBuilder;
import com.example.laptopium.networkmanager.productdetails.ProductDetailsActivity;
import com.example.laptopium.networkmanager.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

//import com.example.laptopium.networkmanager.search.SearchActivity;

public class LandingPageActivity extends AppCompatActivity implements LandingPageRVAdapter.ILanding {


    @Override
    protected void onStart() {
        super.onStart();

//        SharedPreferences sharedPreferences = getSharedPreferences("com.example.laptopium", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("userMail", null);
//        //editor.putString("userName", response.body().getUserName());
//        editor.putString("guestId",null);
//        editor.putString("isLoggedIn","false");
//        editor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        login();
        findViewById(R.id.btn_landing_category).setOnClickListener(view ->
        {
            Intent intent = new Intent(LandingPageActivity.this, CategoryActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.btn_landing_profile).setOnClickListener(view ->
        {
            SharedPreferences sharedPreferences = getSharedPreferences("com.example.laptopium", Context.MODE_PRIVATE);
            if (sharedPreferences.getString("userMail", null) != null) {
                if ("true".equals(sharedPreferences.getString("isLoggedIn", "false"))) {
                    Intent intent = new Intent(LandingPageActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
            } else {
                Toast.makeText(this, "Please login to view profile.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LandingPageActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_landing_home).setOnClickListener(view ->
        {
            Intent intent = new Intent(LandingPageActivity.this, SearchActivity.class);
            startActivity(intent);
        });
//        findViewById(R.id.btn_landing_log).setOnClickListener(view ->
//        {
//            Intent intent = new Intent(LandingPageActivity.this, LoginActivity.class);
//            startActivity(intent);
//        });
        findViewById(R.id.iv_landing_logo).setOnClickListener(
                view -> {
                    Intent intent = new Intent(LandingPageActivity.this, LandingPageActivity.class);
                    startActivity(intent);
                }
        );
        findViewById(R.id.iv_header_cart).setOnClickListener(
                view ->
                {
                    SharedPreferences sharedPreferences = getSharedPreferences("com.example.laptopium", Context.MODE_PRIVATE);
                    if (sharedPreferences.getString("userMail", null) != null) {
                        Intent intent = new Intent(LandingPageActivity.this, MyCartActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Login to view cart.", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        //EditText searchView = findViewById(R.id.et_search);
        // searchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LandingPageActivity.this, SearchActivity.class);
//                startActivity(intent);
//            }
//        });

        getPopular();
    }

    private void getPopular() {
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.laptopium", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        List<Products> productLists = new ArrayList<>();
        //SharedPreferences sharedPreferences = getSharedPreferences("com.example.laptopium", MODE_PRIVATE);
        Retrofit retrofit = RetrofitBuilder.getInstance();
        ILandingPage iLandingPage = retrofit.create(ILandingPage.class);
        String userMail = sharedPreferences.getString("isLoggedIn", "false");
        if (userMail.equals("false")) {
            //SharedPreferences.Editor editor1 = sharedPreferences.edit();
            editor.putString("isGuest", "true");
            editor.apply();
        }
        Call<List<Products>> productList = iLandingPage.getAllProducts();
        Callback<List<Products>> callback = new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                for (Products product : response.body()) {
                    productLists.add(product);
                }
                //Toast.makeText(ProductScroll.this, ""+productLists.toString(), Toast.LENGTH_SHORT).show();
                RecyclerView recyclerView = findViewById(R.id.rv_productlist);
                LandingPageRVAdapter landingPageRVAdapter = new LandingPageRVAdapter(productLists, LandingPageActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(LandingPageActivity.this));
                recyclerView.setAdapter(landingPageRVAdapter);
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

                Toast.makeText(LandingPageActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }

        };
        productList.enqueue(callback);
    }

    void login() {
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.laptopium", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String isLog = sharedPreferences.getString("isLoggedIn", "false");
        if (isLog.equals("false")) {
            Button button1 = findViewById(R.id.btn_landing_log);
            button1.setText("Login");
            button1.setOnClickListener(
                    view ->
                    {
                        Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LandingPageActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
            );
//
        } else {
            Button button = findViewById(R.id.btn_landing_log);
            button.setText("Logout");
            button.setOnClickListener(
                    view ->
                    {
                        editor.remove("userMail");
                        editor.remove("isLoggedIn");
                        Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
//                        editor.putString("userMail",null);
//                        editor.putString("isLoggedIn","false");
                        editor.commit();
                        Intent intent = new Intent(LandingPageActivity.this, LandingPageActivity.class);
                        startActivity(intent);
                    }
            );
        }
    }

    @Override
    public void onUserClick(Products product) {
        Intent intent = new Intent(LandingPageActivity.this, ProductDetailsActivity.class);
        intent.putExtra("productId", product.getId());
        //Log.d("productscroll123", "onUserClick: " + productList.getMerchantId());
        //intent.putExtra("product", new ProductList(product.getProduct(),product.getTypeName(),product.getMerchantId(), (float) product.getPrice(),product.getImageUrl(),product.getCompany(),product.getId()));
        startActivity(intent);
    }
}