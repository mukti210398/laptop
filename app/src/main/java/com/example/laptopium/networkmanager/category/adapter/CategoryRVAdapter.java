package com.example.laptopium.networkmanager.category.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laptopium.R;

import java.util.List;

public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_category_name;
        private final View rootView;

        public ViewHolder(View view) {
            super(view);
            rootView = view;
            tv_category_name = view.findViewById(R.id.tv_category_name);
        }

    }

        private final List<String> categoryLists;
        private final ICategory iCategory;

        public CategoryRVAdapter(List<String> categoryList, ICategory iCategory) {
            this.categoryLists = categoryList;
            this.iCategory = iCategory;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_category, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String category = categoryLists.get(position);
            holder.tv_category_name.setText(String.valueOf(category));
            holder.rootView.setOnClickListener((view -> iCategory.onUserClick(category)));
        }

    @Override
    public int getItemCount() {
        return categoryLists.size();
    }

    public interface ICategory {
            void onUserClick(String  category);
        }
    }


