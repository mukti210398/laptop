package com.example.laptopium.networkmanager.productlist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.laptopium.R;
import com.example.laptopium.networkmanager.productlist.ProductScroll;
import com.example.laptopium.networkmanager.productlist.model.ProductList;

import java.util.List;

public class ProductListRVAdapter extends RecyclerView.Adapter<ProductListRVAdapter.ViewHolder> {

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

    private final List<ProductList> productLists;
    private final ProductScroll iProduct;

    public ProductListRVAdapter(List<ProductList> productLists, ProductScroll iProduct) {
        this.productLists = productLists;
        this.iProduct = iProduct;
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
        ProductList productList = productLists.get(position);
        holder.tv_product_name.setText(String.valueOf(productList.getProduct()));
        holder.tv_category.setText("Company : "+ String.valueOf(productList.getCompany()));
        holder.tv_memory.setText("Memory : "+ String.valueOf(productList.getMemory()));
        holder.tv_price.setText("Price : "+ String.valueOf(productList.getPrice()));
        holder.tv_type.setText(String.valueOf("Type : "+productList.getTypeName()));
        Glide.with(holder.rootView)
                .load(productList.getImageUrl())
                .placeholder(R.drawable.laptop)
                .into(holder.iv_image);
        holder.rootView.setOnClickListener((view -> iProduct.onUserClick(productList)));
    }
    @Override
    public int getItemCount() {
        return productLists.size();
    }

    public interface IProduct {
        void onUserClick(ProductList productList);
    }
}
