package com.example.laptopium.networkmanager.checkout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.laptopium.R;
import com.example.laptopium.ThankYouActivity;
import com.example.laptopium.networkmanager.checkout.model.Order;
import com.example.laptopium.networkmanager.checkout.network.ICheckoutApis;
import com.example.laptopium.networkmanager.checkout.retrofitbuilder.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FinalCheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_checkout);

        ImageView iv_gif = findViewById(R.id.iv_gif);
        double total_price =  getIntent().getDoubleExtra("price",0.0);
        Glide.with(this).asGif()
                .load("https://media1.tenor.com/images/fbe2abc35de102670015793c4ee3b003/tenor.gif")
                .into(iv_gif);


        TextView tv_price = findViewById(R.id.tv_final_checkout_price);
        tv_price.setText("Total amount to be paid : Rs. " + total_price);
        findViewById(R.id.btn_final_checkout).setOnClickListener(view->
        {
            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(),MODE_PRIVATE);

            if (!sharedPreferences.getString("userMail", null).equals("?")) {
                String userMail = sharedPreferences.getString("userMail", null);
                int index = userMail.indexOf("@");
                String userId = userMail.substring(0, index);
                Retrofit retrofit = RetrofitBuilder.getInstance();
                ICheckoutApis iCheckoutApis = retrofit.create(ICheckoutApis.class);
                Call<List<Order>> cartDetails = iCheckoutApis.confirmOrder(userId);
                Callback<List<Order>> callback = new Callback<List<Order>>() {
                    @Override
                    public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                        Toast.makeText(FinalCheckoutActivity.this, "Order placed.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(FinalCheckoutActivity.this, ThankYouActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<List<Order>> call, Throwable t) {
                        Toast.makeText(FinalCheckoutActivity.this, "Cannot load the cart.", Toast.LENGTH_SHORT).show();
                    }
                };
                cartDetails.enqueue(callback);

                //Call<Response>
           }
            else {
                Toast.makeText(this, "Unable to order.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}