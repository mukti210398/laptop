package com.example.laptopium.networkmanager.authentication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.laptopium.R;
import com.example.laptopium.networkmanager.authentication.model.UserDetails;
import com.example.laptopium.networkmanager.authentication.network.IAuthentication;
import com.example.laptopium.networkmanager.authentication.retrofitbuilder.RetrofitBuilder;
import com.example.laptopium.networkmanager.orderhistory.OrderHistoryActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        findViewById(R.id.btn_profile_change_password).setOnClickListener(view->
        {
            Intent intent = new Intent(ProfileActivity.this, ChangePasswordActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.btn_profile_order_history).setOnClickListener(view->
        {
            Intent intent = new Intent(ProfileActivity.this, OrderHistoryActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.btn_profile_login_history).setOnClickListener(view->
        {
            Intent intent = new Intent(ProfileActivity.this, LoginHistoryActivity.class);
            startActivity(intent);
        });


        SharedPreferences sharedPreferences = getSharedPreferences("com.example.laptopium", Context.MODE_PRIVATE);
        if(sharedPreferences.getString("userMail",null)!=null)
        {
            if("true".equals(sharedPreferences.getString("isLoggedIn","false")))
            {
                String email = sharedPreferences.getString("userMail","");
                Retrofit retrofit = RetrofitBuilder.getInstance();
                IAuthentication iAuthentication = retrofit.create(IAuthentication.class);
                Call<UserDetails> userDetailsCall  = iAuthentication.getUserDetails(email);
                userDetailsCall.enqueue(new Callback<UserDetails>() {
                    @Override
                    public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                        TextView et_name = findViewById(R.id.et_profile_edit_name);
                        et_name.setText(response.body().getUserName());
                        TextView et_email = findViewById(R.id.et_profile_email_edit);
                        et_email.setText(response.body().getUserMail());
                        ImageView iv_profile = findViewById(R.id.iv_profile_image);
                        Glide.with(ProfileActivity.this)
                                .load(response.body().getUserImageUrl())
                                .placeholder(R.drawable.placeholder_profile)
                                .into(iv_profile);
                    }

                    @Override
                    public void onFailure(Call<UserDetails> call, Throwable t) {

                    }
                });
            }

        }
        else {
            Toast.makeText(this, "Please login to view profile.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ProfileActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }


}