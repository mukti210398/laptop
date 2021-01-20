package com.example.laptopium.networkmanager.productlist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laptopium.R;
import com.example.laptopium.networkmanager.productdetails.ProductDetailsActivity;
import com.example.laptopium.networkmanager.productlist.adapter.ProductListRVAdapter;
import com.example.laptopium.networkmanager.productlist.model.ProductList;
import com.example.laptopium.networkmanager.productlist.network.IProductListApi;
import com.example.laptopium.networkmanager.productlist.retrofitbuilder.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductScroll extends AppCompatActivity implements ProductListRVAdapter.IProduct {
    List<ProductList> productLists =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_scroll);
        displayAllProducts();

    }

    private void displayAllProducts() {
        String get_category_name = getIntent().getStringExtra("categoryName");
        //Toast.makeText(this, "" + get_category_name, Toast.LENGTH_SHORT).show();
        Retrofit retrofit = RetrofitBuilder.getInstance();
        IProductListApi iProductListApi = retrofit.create(IProductListApi.class);


        if(! "categoryName".isEmpty()) {
            Call<List<ProductList>> productList = iProductListApi.getProductList(get_category_name);
            Callback<List<ProductList>> callback = new Callback<List<ProductList>>() {
                @Override
                public void onResponse(Call<List<ProductList>> call, Response<List<ProductList>> response) {
                    //Toast.makeText(ProductScroll.this, "Yayyy", Toast.LENGTH_SHORT).show();
                    for (ProductList product : response.body()) {
                        productLists.add(product);
                    }
                    //Toast.makeText(ProductScroll.this, ""+productLists.toString(), Toast.LENGTH_SHORT).show();
                    RecyclerView recyclerView = findViewById(R.id.rv_productlist);
                    ProductListRVAdapter productListRVAdapter = new ProductListRVAdapter(productLists, ProductScroll.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ProductScroll.this));
                    recyclerView.setAdapter(productListRVAdapter);
                }

                @Override
                public void onFailure(Call<List<ProductList>> call, Throwable t) {
                    Toast.makeText(ProductScroll.this, "Faiiled on Product Scroll", Toast.LENGTH_SHORT).show();
                }
            };
            productList.enqueue(callback);
        }
        else{
            Toast.makeText(this, "Please select category", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUserClick(ProductList product) {
        Intent intent = new Intent(ProductScroll.this, ProductDetailsActivity.class);
        intent.putExtra("productId",product.getId());
        //Log.d("productscroll123", "onUserClick: " + product.getMerchantId());
        //intent.putExtra("product", new ProductList(product.getProduct(),product.getTypeName(),product.getMerchantId(), (float) product.getPrice(),product.getImageUrl(),product.getCompany(),product.getId()));
        startActivity(intent);
    }
}