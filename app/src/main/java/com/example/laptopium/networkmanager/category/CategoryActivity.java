package com.example.laptopium.networkmanager.category;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laptopium.R;
import com.example.laptopium.networkmanager.category.adapter.CategoryRVAdapter;
import com.example.laptopium.networkmanager.category.network.ICategoryApi;
import com.example.laptopium.networkmanager.productdetails.retrofitbuilder.RetrofitBuilder;
import com.example.laptopium.networkmanager.productlist.ProductScroll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryActivity extends AppCompatActivity implements CategoryRVAdapter.ICategory {
    List<String > categoryLists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setCategoryList();
    }

    // Display all categories
    private void setCategoryList() {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        ICategoryApi iCategory = retrofit.create(ICategoryApi.class);
        Call<List<String>> categoryList = iCategory.getCategoryList();
        Callback<List<String>> callback = new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                //Log.d("12345", "onResponse: " + response.body());
                for(String category:response.body())
                {
                    categoryLists.add(category);
                }
                RecyclerView recyclerView = findViewById(R.id.rv_category_list);
                CategoryRVAdapter categoryRVAdapter = new CategoryRVAdapter(categoryLists,CategoryActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(CategoryActivity.this));
                recyclerView.setAdapter(categoryRVAdapter);
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(CategoryActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        };
        categoryList.enqueue(callback);
    }

    @Override
    public void onUserClick(String categoryList) {
        //Toast.makeText(this, "You clicked on " + categoryList, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CategoryActivity.this, ProductScroll.class);
        intent.putExtra("categoryName",categoryList);
        startActivity(intent);
    }
}