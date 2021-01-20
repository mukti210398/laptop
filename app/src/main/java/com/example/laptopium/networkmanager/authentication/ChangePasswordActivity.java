package com.example.laptopium.networkmanager.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.laptopium.R;
import com.example.laptopium.networkmanager.authentication.model.ChangePassword;
import com.example.laptopium.networkmanager.authentication.network.IAuthentication;
import com.example.laptopium.networkmanager.authentication.retrofitbuilder.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        findViewById(R.id.btn_change_password).setOnClickListener(
                view->
                {
                    changePassword();
                }
        );
    }


    private void changePassword() {
        EditText et_mail = findViewById(R.id.et_change_password_email);
        String str_mail = et_mail.getText().toString();
        EditText et_password = findViewById(R.id.et_change_password_old);
        String str_password = et_password.getText().toString();
        EditText et_password_new = findViewById(R.id.et_change_password_new);
        String str_new_password = et_password_new.getText().toString();
        ChangePassword changePassword =new ChangePassword();
        changePassword.setNewUserPswd(str_new_password);
        changePassword.setOldUserPswd(str_password);
        changePassword.setUserMail(str_mail);

        Retrofit retrofit = RetrofitBuilder.getInstance();
        IAuthentication iAuthentication = retrofit.create(IAuthentication.class);
        Call<Boolean> changedPassword = iAuthentication.sendChangedPassword(changePassword);
        Callback<Boolean> callback = new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.body() == true) {
                    Intent intent = new Intent(ChangePasswordActivity.this,ProfileActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ChangePasswordActivity.this, "Invaild credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(ChangePasswordActivity.this, "Failed to change", Toast.LENGTH_SHORT).show();
            }
        } ;
        changedPassword.enqueue(callback);
    }

}