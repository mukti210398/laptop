package com.example.laptopium.networkmanager.authentication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.laptopium.R;
import com.example.laptopium.networkmanager.authentication.model.LoginUser;
import com.example.laptopium.networkmanager.authentication.model.UpdateUserId;
import com.example.laptopium.networkmanager.authentication.model.UserRegistered;
import com.example.laptopium.networkmanager.authentication.network.IAuthentication;
import com.example.laptopium.networkmanager.authentication.retrofitbuilder.RetrofitBuilder;
import com.example.laptopium.networkmanager.cart.model.CartDetails;
import com.example.laptopium.networkmanager.landingpage.LandingPageActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

//    GoogleSignInClient mGoogleSignInClient;
//     int RC_SIGN_IN=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        findViewById(R.id.btn_login_login).setOnClickListener(view->
        {
            verifyLogin();
        });
        findViewById(R.id.btn_login_reg).setOnClickListener(view->
        {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//        CardView cardView = findViewById(R.id.cv_login_google);
//        cardView.setOnClickListener(view->
//        {
//            switch (view.getId()) {
//                case R.id.cv_login_google:
//                    signIn();
//                    break;
//                // ...
//            }
//        });
    }

//    private void signIn() {
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            // The Task returned from this call is always completed, no need to attach
//            // a listener.
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
//        }
//    }
//
//    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//
//            // Signed in successfully, show authenticated UI.
//            Intent intent = new Intent();
//            setResult(2,intent);
//            finish();
//
//        } catch (ApiException e) {
//            // The ApiException status code indicates the detailed failure reason.
//            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
//
//        }
//    }

//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//
//    }

    private void verifyLogin() {
        EditText et_login = findViewById(R.id.et_login_mail);
        String str_mail = et_login.getText().toString();
        EditText et_password = findViewById(R.id.et_login_password);
        String str_password = et_password.getText().toString();
       // Log.d("Mukti", "verifyLogin: "+str_mail+"  " + str_password);
        Retrofit retrofit = RetrofitBuilder.getInstance();
        IAuthentication iAuthentication = retrofit.create(IAuthentication.class);
        Call<UserRegistered> loginUserCall = iAuthentication.sendLoginDetails(new LoginUser(str_mail,str_password));
       // Log.d("Mukti", "verifyLogin: ");
        Callback<UserRegistered> callback = new Callback<UserRegistered>() {
            @Override
            public void onResponse(Call<UserRegistered> call, Response<UserRegistered> response) {
                if(response.isSuccessful() == true) {
                    SharedPreferences sharedPreferences = getSharedPreferences("com.example.laptopium", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userMail", str_mail);
                    editor.putString("isLoggedIn","true");
                    editor.putString("userName", response.body().getUserId());
                    if(sharedPreferences.getString("guest",null)!=null)
                    {
                        String guest = sharedPreferences.getString("guest",null);

                        Retrofit retrofit = RetrofitBuilder.getInstance();
                        IAuthentication iAuthentication = retrofit.create(IAuthentication.class);
                        Call<List<CartDetails>> updateUserIdCall = iAuthentication.updateUserId(new UpdateUserId(str_mail,guest));
                        Callback<List<CartDetails>> callback1 = new Callback<List<CartDetails>>() {
                            @Override
                            public void onResponse(Call<List<CartDetails>> call, Response<List<CartDetails>> response) {
                                Toast.makeText(LoginActivity.this, "Cart updated.", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<List<CartDetails>> call, Throwable t) {
                                Toast.makeText(LoginActivity.this, "cartLost", Toast.LENGTH_SHORT).show();
                            }
                        };
                        editor.remove("guest");
                    }
                    editor.commit();
                //    Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, LandingPageActivity.class);
                    startActivity(intent);
                    //finish();
                    //Toast.makeText(LoginActivity.this, "Opps!! Something went wrong.\n Try again.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials.\n Try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserRegistered> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Opps!! Something went wrong.\n Try again.", Toast.LENGTH_SHORT).show();
            }
        };
        loginUserCall.enqueue(callback);
    }

}