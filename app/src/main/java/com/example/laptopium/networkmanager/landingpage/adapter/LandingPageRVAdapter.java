package com.example.laptopium.networkmanager.landingpage.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.laptopium.R;
import com.example.laptopium.networkmanager.landingpage.LandingPageActivity;
import com.example.laptopium.networkmanager.landingpage.model.Products;

import java.util.List;

public class LandingPageRVAdapter extends RecyclerView.Adapter<LandingPageRVAdapter.ViewHolder> {

        public static class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView tv_category;
            private final TextView tv_product_name;
            private final TextView tv_price;
            private final TextView tv_memory;
            private final TextView tv_type;
            private final ImageView iv_image;
            private final View rootView;

            public ViewHolder(View view) {
                super(view);
                rootView = view;
                tv_category = view.findViewById(R.id.tv_product_list_category);
                tv_product_name = view.findViewById(R.id.tv_product_list_product_name);
                tv_memory = view.findViewById(R.id.tv_product_list_memory);
                tv_price = view.findViewById(R.id.tv_product_list_price);
                iv_image = view.findViewById(R.id.iv_product_list_image);
                tv_type = view.findViewById(R.id.tv_product_list_type);
            }

        }

        private final List<Products> productLists;
        private final LandingPageActivity landingPageActivity;

        public LandingPageRVAdapter(List<Products> productLists, LandingPageActivity iProduct) {
            this.productLists = productLists;
            this.landingPageActivity = iProduct;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_product_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Products productList = productLists.get(position);
            holder.tv_product_name.setText(String.valueOf(productList.getProduct()));
            holder.tv_category.setText(String.valueOf(productList.getCompany()));
            holder.tv_memory.setText(String.valueOf(productList.getMemory()));
            holder.tv_price.setText(String.valueOf(productList.getPrice()));
            holder.tv_type.setText(String.valueOf(productList.getTypeName()));
            Glide.with(holder.rootView)
                    .load(productList.getImageUrl())
                    .placeholder(R.drawable.laptop)
                    .into(holder.iv_image);
            holder.rootView.setOnClickListener((view -> landingPageActivity.onUserClick(productList)));
        }
        @Override
        public int getItemCount() {
            return productLists.size();
        }

        public interface ILanding {
            void onUserClick(Products productList);
        }
    }


