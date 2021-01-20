package com.example.laptopium.networkmanager.search.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.laptopium.R;
import com.example.laptopium.networkmanager.search.SearchActivity;
import com.example.laptopium.networkmanager.search.model.SearchProduct;

import java.util.List;

public class SearchRVAdapter extends RecyclerView.Adapter<SearchRVAdapter.ViewHolder> {

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

    private final List<SearchProduct> searchProductList;
    private final SearchActivity iSearch;

    public SearchRVAdapter(List<SearchProduct> searchProducts, SearchActivity iSearch) {
        this.searchProductList = searchProducts;
        this.iSearch = iSearch;
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
        if(searchProductList.size()==0)
        {
            Toast.makeText(iSearch, "No products available.", Toast.LENGTH_SHORT).show();
        }else {
            SearchProduct searchProduct = searchProductList.get(position);
            holder.tv_product_name.setText(String.valueOf(searchProduct.getProduct()));
            holder.tv_category.setText(String.valueOf("Category : "+ searchProduct.getCompany()));
            holder.tv_memory.setText("Memory : "+ String.valueOf(searchProduct.getMemory()));
            holder.tv_price.setText("Price : "+ String.valueOf(searchProduct.getPrice()));
            holder.tv_type.setText("Type : "+String.valueOf(searchProduct.getTypeName()));
            Glide.with(holder.rootView)
                    .load(searchProduct.getImageUrl())
                    .placeholder(R.drawable.laptop)
                    .into(holder.iv_image);
            holder.rootView.setOnClickListener((view -> iSearch.onUserClick(searchProduct)));
        }
    }
    @Override
    public int getItemCount() {
        return searchProductList.size();
    }

    public interface ISearch {
        void onUserClick(SearchProduct searchProduct);
    }
}


