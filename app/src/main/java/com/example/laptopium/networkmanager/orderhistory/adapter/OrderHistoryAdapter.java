package com.example.laptopium.networkmanager.orderhistory.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laptopium.R;
import com.example.laptopium.networkmanager.orderhistory.OrderHistoryActivity;
import com.example.laptopium.networkmanager.orderhistory.model.OrderHistory;
import com.example.laptopium.networkmanager.orderhistory.model.Rating;
import com.example.laptopium.networkmanager.orderhistory.network.IOrderHistoryApi;
import com.example.laptopium.networkmanager.orderhistory.retrofitbuilder.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_orderId;
        private final TextView tv_price;
        private final Button btn_rate;
        private final TextView tv_timestamp;
        private final View rootView;
        private final EditText et_rate;

        public ViewHolder(View view) {
            super(view);
            rootView = view;
            tv_orderId = view.findViewById(R.id.tv_order_history_orderId);
            tv_price = view.findViewById(R.id.tv_order_history_price);
            btn_rate = view.findViewById(R.id.btn_order_history_rate);
            tv_timestamp = view.findViewById(R.id.tv_order_timestap);
            et_rate = view.findViewById(R.id.tv_order_history_rating);
        }

    }

    private final List<OrderHistory> orderHistoryList;
    private final OrderHistoryActivity orderHistory;

    public OrderHistoryAdapter(List<OrderHistory> orderHistoryList, OrderHistoryActivity orderHistory) {
        this.orderHistoryList = orderHistoryList;
        this.orderHistory = orderHistory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_order_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderHistory orderHistorys = orderHistoryList.get(position);
        holder.tv_orderId.setText("Order ID : " + String.valueOf(orderHistorys.getOrderId()));
        holder.tv_price.setText("Paid : " + String.valueOf(orderHistorys.getPrice()));
        holder.tv_timestamp.setText("Order Date : " + String.valueOf(orderHistorys.getOrderTimeStamp()));
        if(orderHistorys.getRating()>=1 && orderHistorys.getRating()<=5)
        {
            holder.et_rate.setText(String.valueOf(orderHistorys.getRating()));
        }

        holder.rootView.setOnClickListener((view -> orderHistory.onUserClick(orderHistorys)));
        holder.btn_rate.setOnClickListener(view ->
        {
            if(! holder.et_rate.getText().toString().equals("")) {
                int rating = Integer.parseInt(holder.et_rate.getText().toString());
                if (rating > 0 && rating <= 5) {
                    Rating rating1 = new Rating(orderHistorys.getOrderId(), rating);
                    Retrofit retrofit = RetrofitBuilder.getInstance();
                    IOrderHistoryApi iOrderHistoryApi = retrofit.create(IOrderHistoryApi.class);
                    Call<Boolean> sendRating = iOrderHistoryApi.sendRating(rating1);
                    Callback<Boolean> callback = new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            Toast.makeText(orderHistory, "Rating Updated", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Toast.makeText(orderHistory, "Opps!! Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    };
                    sendRating.enqueue(callback);
                }
                else {
                    Toast.makeText(orderHistory, "Rate between 1 to 5", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    @Override
    public int getItemCount() {
        return orderHistoryList.size();
    }

    public interface IOrderHistory {
        void onUserClick(OrderHistory orderHistory);
    }
}
