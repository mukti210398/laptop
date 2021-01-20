package com.example.laptopium.networkmanager.authentication;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.laptopium.R;
import com.example.laptopium.networkmanager.authentication.model.RegisterUser;
import com.example.laptopium.networkmanager.authentication.model.UserRegistered;
import com.example.laptopium.networkmanager.authentication.network.IAuthentication;
import com.example.laptopium.networkmanager.authentication.retrofitbuilder.RetrofitBuilder;
import com.example.laptopium.networkmanager.landingpage.LandingPageActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViewById(R.id.btn_reg_register).setOnClickListener(view ->
        {
            verifyUser();
        });
        findViewById(R.id.btn_reg_login).setOnClickListener(view ->
        {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void verifyUser() {
        EditText et_mail = findViewById(R.id.et_reg_mail);
        String str_mail = et_mail.getText().toString();
        EditText et_password = findViewById(R.id.et_reg_password);
        String str_password = et_password.getText().toString();
        EditText et_name = findViewById(R.id.et_reg_name);
        String str_name = et_name.getText().toString();

        RegisterUser registerUser = new RegisterUser();
        registerUser.setUserName(str_name);
        registerUser.setUserMail(str_mail);
        registerUser.setUserPswd(str_password);
        registerUser.setUserImage(null);

        //Log.d(TAG, "verifyUser: " + );


        Retrofit retrofit = RetrofitBuilder.getInstance();
        IAuthentication iAuthentication = retrofit.create(IAuthentication.class);
        Call<UserRegistered> registerDetails = iAuthentication.sendRegisterDetails(registerUser);
        registerDetails.enqueue(new Callback<UserRegistered>() {
            @Override
            public void onResponse(Call<UserRegistered> call, Response<UserRegistered> response) {

                if (response.body() == null) {
                    Toast.makeText(RegisterActivity.this, "Already registered.", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("com.example.laptopium", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userMail", null);
                    //editor.putString("userName", response.body().getUserName());

                    editor.putString("isLoggedIn","false");
                    editor.apply();
                } else {
                    SharedPreferences sharedPreferences = getSharedPreferences("com.example.laptopium", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userMail", response.body().getUserMail());
                    //editor.putString("userName", response.body().getUserName());
                    editor.putString("isLoggedIn","true");
                    editor.apply();
                    Toast.makeText(RegisterActivity.this, "LOGGED IN", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LandingPageActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<UserRegistered> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}