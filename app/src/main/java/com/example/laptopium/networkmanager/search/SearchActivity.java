package com.example.laptopium.networkmanager.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laptopium.R;
import com.example.laptopium.networkmanager.productdetails.ProductDetailsActivity;
import com.example.laptopium.networkmanager.search.adapter.SearchRVAdapter;
import com.example.laptopium.networkmanager.search.model.SearchProduct;
import com.example.laptopium.networkmanager.search.network.ISearchApi;
import com.example.laptopium.networkmanager.search.retrofitbuilder.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchActivity extends AppCompatActivity implements SearchRVAdapter.ISearch {

    List<SearchProduct> searchProductList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Button button =findViewById(R.id.btn_search_productList);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchProducts();

            }
        });


    }

    void searchProducts() {
//        Toast.makeText(this, "on btn click", Toast.LENGTH_SHORT).show();
        Retrofit retrofit = RetrofitBuilder.getInstance();
       ISearchApi iSearchApi = retrofit.create(ISearchApi.class);
       EditText editText =findViewById(R.id.et_search_text);
        //Toast.makeText(this, "abc", Toast.LENGTH_SHORT).show();
        Call<List<SearchProduct>> search = iSearchApi.getSearch(editText.getText().toString());
        search.enqueue(new Callback<List<SearchProduct>>() {
            @Override
            public void onResponse(Call<List<SearchProduct>> call, Response<List<SearchProduct>> response) {
                searchProductList.clear();
                for(SearchProduct searchProduct: response.body())
                {
                    searchProductList.add(searchProduct);
                   // Log.d("aaaaaaa", "onResponse: "+searchProduct);
                }
                RecyclerView recyclerView = findViewById(R.id.rv_search);
                SearchRVAdapter searchRVAdapter = new SearchRVAdapter(searchProductList, SearchActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                recyclerView.setAdapter(searchRVAdapter);
            }

            @Override
            public void onFailure(Call<List<SearchProduct>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onUserClick(SearchProduct searchProduct) {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SearchActivity.this, ProductDetailsActivity.class);
        intent.putExtra("productId",searchProduct.getId());
        startActivity(intent);

    }
}