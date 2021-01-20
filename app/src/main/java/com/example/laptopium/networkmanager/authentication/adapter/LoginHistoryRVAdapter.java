package com.example.laptopium.networkmanager.authentication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laptopium.R;
import com.example.laptopium.networkmanager.authentication.LoginHistoryActivity;

import java.util.List;

public class LoginHistoryRVAdapter extends RecyclerView.Adapter<LoginHistoryRVAdapter.ViewHolder> {

        public static class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView tv_history;
            private final View rootView;
            public ViewHolder(View view) {
                super(view);
                rootView = view;
                tv_history = view.findViewById(R.id.tv_login_history);
            }
        }

        private final List<String> historyList;
        private final LoginHistoryActivity loginHistoryActivity;

        public LoginHistoryRVAdapter(List<String> historyLists,LoginHistoryActivity loginHistoryActivity) {
            this.historyList = historyLists;
            this.loginHistoryActivity = loginHistoryActivity;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_login_history, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull LoginHistoryRVAdapter.ViewHolder holder, int position) {
            String userDetails = historyList.get(position);

            holder.tv_history.setText(userDetails);
        }

        @Override
        public int getItemCount() {
            return historyList.size();
        }
    }



