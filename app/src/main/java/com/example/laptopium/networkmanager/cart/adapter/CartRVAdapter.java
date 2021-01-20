package com.example.laptopium.networkmanager.cart.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.laptopium.R;
import com.example.laptopium.networkmanager.cart.MyCartActivity;
import com.example.laptopium.networkmanager.cart.model.CartDetails;
import com.example.laptopium.networkmanager.cart.network.ICartApi;
import com.example.laptopium.networkmanager.cart.retrofitbuilder.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartRVAdapter extends RecyclerView.Adapter<CartRVAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_product_name;
        private final TextView tv_product_price;
        private final Button btn_remove;
        private final ImageView iv_image;
        private final View rootView;
        private final Button btn_addQty;
        private final Button btn_minusQty;
        private final TextView tv_qty;

        public ViewHolder(View view) {
            super(view);
            rootView = view;
            tv_product_name = view.findViewById(R.id.tv_cart_product_name);
            tv_product_price = view.findViewById(R.id.tv_cart_price);
            iv_image = view.findViewById(R.id.iv_cart_product_image);
            btn_remove = view.findViewById(R.id.btn_cart_remove);
            btn_addQty = view.findViewById(R.id.btn_cart_add_qty);
            btn_minusQty = view.findViewById(R.id.btn_cart_minus_qty);
            tv_qty = view.findViewById(R.id.tv_cart_qty);

        }

    }

    private final List<CartDetails> cartDetailsList;
    private final MyCartActivity myCartActivity;
    private double totalPrice = 0;

    public CartRVAdapter(List<CartDetails> cartDetails, MyCartActivity myCartActivity) {
        this.cartDetailsList = cartDetails;
        this.myCartActivity = myCartActivity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_cart_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CartDetails cartDetail = cartDetailsList.get(position);
        holder.tv_product_name.setText(String.valueOf(cartDetail.getProductName()));
        holder.tv_product_price.setText(String.valueOf(cartDetail.getPrice()));
        holder.tv_qty.setText(String.valueOf(cartDetail.getQuantity()));
        Glide.with(holder.rootView)
                .load(cartDetail.getProductImageUrl())
                .placeholder(R.drawable.laptop)
                .into(holder.iv_image);
        holder.btn_addQty.setOnClickListener(view ->
        {
            int qty = Integer.parseInt(holder.tv_qty.getText().toString());
            if (qty > 1) {
                if (qty <= 1) {
                    holder.btn_addQty.setEnabled(false);
                } else {
                    qty--;
                    holder.tv_qty.setText(String.valueOf(qty));
                }
                cartDetail.setQuantity(qty);
                //myCartActivity.onUpdateQty(cartDetail);
                Retrofit retrofit = RetrofitBuilder.getInstance();
                ICartApi iCartApi = retrofit.create(ICartApi.class);
                Call<List<CartDetails>> myProd = iCartApi.updateProduct(cartDetail);
                Callback<List<CartDetails>> callback = new Callback<List<CartDetails>>() {
                    @Override
                    public void onResponse(Call<List<CartDetails>> call, Response<List<CartDetails>> response) {
                        int index = cartDetailsList.indexOf(cartDetail);
                        cartDetailsList.remove(index);
                        CartRVAdapter.this.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<CartDetails>> call, Throwable t) {
                        Toast.makeText(myCartActivity, "Opps!! Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                };
                myProd.enqueue(callback);
            } else {
                Toast.makeText(myCartActivity, "Maximum quantity reached.", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btn_minusQty.setOnClickListener(view1 -> {
            int qty = Integer.parseInt(holder.tv_qty.getText().toString());
            if (cartDetail.getQuantity() > qty) {
                holder.btn_minusQty.setEnabled(true);
                qty++;
                holder.tv_qty.setText(String.valueOf(qty));
                cartDetail.setQuantity(qty);
                Retrofit retrofit = RetrofitBuilder.getInstance();
                ICartApi iCartApi = retrofit.create(ICartApi.class);
                Call<List<CartDetails>> myProd = iCartApi.updateProduct(cartDetail);
                Callback<List<CartDetails>> callback = new Callback<List<CartDetails>>() {
                    @Override
                    public void onResponse(Call<List<CartDetails>> call, Response<List<CartDetails>> response) {
                        int index = cartDetailsList.indexOf(cartDetail);
                        cartDetailsList.remove(index);
                        CartRVAdapter.this.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<CartDetails>> call, Throwable t) {
                        Toast.makeText(myCartActivity, "Opps!! Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                };
                myProd.enqueue(callback);
            } else {
                Toast.makeText(myCartActivity, "Maximum quantity reached.", Toast.LENGTH_SHORT).show();
            }
        });
        //Toast.makeText(myCartActivity, "Before remove btn", Toast.LENGTH_SHORT).show();
        holder.btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = RetrofitBuilder.getInstance();
                //Toast.makeText(myCartActivity, "removedddd", Toast.LENGTH_SHORT).show();
                ICartApi iCartApi = retrofit.create(ICartApi.class);
                Call<List<CartDetails>> myProd = iCartApi.deleteProduct(cartDetail);
                Callback<List<CartDetails>> callback = new Callback<List<CartDetails>>() {
                    @Override
                    public void onResponse(Call<List<CartDetails>> call, Response<List<CartDetails>> response) {
                        cartDetailsList.remove(position);
                        CartRVAdapter.this.notifyDataSetChanged();

//                        Intent intent = new Intent(myCartActivity,MyCartActivity.class);
                        myCartActivity.load();
                    }

                    @Override
                    public void onFailure(Call<List<CartDetails>> call, Throwable t) {
                        Toast.makeText(myCartActivity, "Opps!! Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                };
                myProd.enqueue(callback);
            }
        });


//        holder.btn_remove.setOnClickListener(view2->{
//            Toast.makeText(myCartActivity, "btn remove", Toast.LENGTH_SHORT).show();

//        });
        //holder.tv_product_price.setText(String.valueOf(cartDetail.get));

//
        holder.rootView.setOnClickListener((view -> myCartActivity.onCartClicked(cartDetail)));
    }


    @Override
    public int getItemCount() {
        return cartDetailsList.size();
    }

    public interface ICart {

        void onCartClicked(CartDetails cartDetails);
        void load();
        //void onUpdateQty(CartDetails cartDetails);
    }
}
