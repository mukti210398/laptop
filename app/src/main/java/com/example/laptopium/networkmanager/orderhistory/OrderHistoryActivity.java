package com.example.laptopium.networkmanager.orderhistory;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laptopium.R;
import com.example.laptopium.networkmanager.orderhistory.adapter.OrderHistoryAdapter;
import com.example.laptopium.networkmanager.orderhistory.model.OrderHistory;
import com.example.laptopium.networkmanager.orderhistory.network.IOrderHistoryApi;
import com.example.laptopium.networkmanager.productdetails.retrofitbuilder.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderHistoryActivity extends AppCompatActivity implements OrderHistoryAdapter.IOrderHistory {

    List<OrderHistory> orderHistoryList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        Retrofit retrofit = RetrofitBuilder.getInstance();
        IOrderHistoryApi iOrderHistoryApi = retrofit.create(IOrderHistoryApi.class);
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.laptopium",MODE_PRIVATE);

        if (sharedPreferences.getString("userMail", null)!=null) {
            String userMail = sharedPreferences.getString("userMail", null);
            int index = userMail.indexOf("@");
            String userId = userMail.substring(0, index);
            Call<List<OrderHistory>> orderHistory = iOrderHistoryApi.getOrderHistory(userId);
            Callback<List<OrderHistory>> callback = new Callback<List<OrderHistory>>() {
                @Override
                public void onResponse(Call<List<OrderHistory>> call, Response<List<OrderHistory>> response) {
                    for (OrderHistory orderHistory1 : response.body()) {
                        orderHistoryList.add(orderHistory1);
                    }
                    RecyclerView recyclerView = findViewById(R.id.rv_order_history);
                    OrderHistoryAdapter recyclerViewAdapter = new OrderHistoryAdapter(orderHistoryList, OrderHistoryActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(OrderHistoryActivity.this));
                    recyclerView.setAdapter(recyclerViewAdapter);
                }

                @Override
                public void onFailure(Call<List<OrderHistory>> call, Throwable t) {
                    Toast.makeText(OrderHistoryActivity.this, "Unable to load cart.", Toast.LENGTH_SHORT).show();
                }
            };
            orderHistory.enqueue(callback);
        }
        else   {
            Toast.makeText(this, "You are not logged in.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUserClick(OrderHistory orderHistory) {

    }
}