package com.example.laptopium.networkmanager.authentication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laptopium.R;
import com.example.laptopium.networkmanager.authentication.adapter.LoginHistoryRVAdapter;
import com.example.laptopium.networkmanager.authentication.model.UserDetails;
import com.example.laptopium.networkmanager.authentication.network.IAuthentication;
import com.example.laptopium.networkmanager.authentication.retrofitbuilder.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginHistoryActivity extends AppCompatActivity {


    List<String> logs = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_history);
        loginStamp();
    }


    void loginStamp() {
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.laptopium", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String mail = sharedPreferences.getString("userMail", null);
        if (mail != null) {
            Retrofit retrofit = RetrofitBuilder.getInstance();
            IAuthentication iAuthentication = retrofit.create(IAuthentication.class);

            Call<UserDetails> userDetailsCall = iAuthentication.getUserDetails(mail);
            userDetailsCall.enqueue(new Callback<UserDetails>() {
                @Override
                public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                    String loginTimeStamp = response.body().getLoginTimeStamp();
                    String[] log = loginTimeStamp.split(",");
                    for (String log1 : log) {
                        logs.add(log1);
                    }
                    RecyclerView recyclerView = findViewById(R.id.rv_login_history);
                    LoginHistoryRVAdapter loginHistoryRVAdapter = new LoginHistoryRVAdapter(logs, LoginHistoryActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(LoginHistoryActivity.this));
                    recyclerView.setAdapter(loginHistoryRVAdapter);
                }

                @Override
                public void onFailure(Call<UserDetails> call, Throwable t) {
                    Toast.makeText(LoginHistoryActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

