package com.example.laptopium.networkmanager.productdetails.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laptopium.R;
import com.example.laptopium.networkmanager.productdetails.ProductDetailsActivity;
import com.example.laptopium.networkmanager.productdetails.model.MerchantProduct;

import java.util.List;

public class OtherMerchantsRVAdapter  extends RecyclerView.Adapter<OtherMerchantsRVAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_other_merchant_details;
        private final TextView tv_other_merchant_price;
        private final TextView tv_other_merchant_rate;
        private final View rootView;
//        private final CardView cardView;
        public ViewHolder(View view) {
            super(view);
            rootView = view;
            tv_other_merchant_details = view.findViewById(R.id.tv_other_merchant_details);
            tv_other_merchant_price = view.findViewById(R.id.tv_other_merchant_price);
            tv_other_merchant_rate = view.findViewById(R.id.tv_other_merchant_rating);
//            cardView=view.findViewById(R.id.cardView_other_merchant_card);
        }

    }

        private final List<MerchantProduct> otherMerchantsList;
        private final ProductDetailsActivity productDetailsActivity;

        public OtherMerchantsRVAdapter(List<MerchantProduct> otherMerchantsList, ProductDetailsActivity productDetailsActivity) {
            this.otherMerchantsList = otherMerchantsList;
            this.productDetailsActivity = productDetailsActivity;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_show_other_merchants, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            MerchantProduct otherMerchants = otherMerchantsList.get(position);
            holder.tv_other_merchant_details.setText("Merchant Name : "+ String.valueOf(otherMerchants.getMerchantName()));
            holder.tv_other_merchant_price.setText("Price offered : "+ String.valueOf(otherMerchants.getPrice()));
            holder.tv_other_merchant_rate.setText("Rating : "+ String.valueOf(otherMerchants.getMerchantProductRating()));

            //holder.tv_emp_lname.setText("Title : "+myEmployee.getLastName());
            holder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(productDetailsActivity, "xyzzzzzzz", Toast.LENGTH_SHORT).show();
                }
            });
//            holder.rootView.setOnClickListener((view -> productDetailsActivity.onUserClick1(otherMerchants)));
//            Toast.makeText(productDetailsActivity, "xyzzz", Toast.LENGTH_SHORT).show();

        }

        @Override
        public int getItemCount() {
            return otherMerchantsList.size();
        }

        public interface IMerchant {
            void onUserClick1(MerchantProduct merchantProduct);
        }




    }

