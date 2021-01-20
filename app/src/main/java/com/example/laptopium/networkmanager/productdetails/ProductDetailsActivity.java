package com.example.laptopium.networkmanager.productdetails;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.laptopium.R;
import com.example.laptopium.networkmanager.ErrorActivity;
import com.example.laptopium.networkmanager.cart.MyCartActivity;
import com.example.laptopium.networkmanager.productdetails.adapter.OtherMerchantsRVAdapter;
import com.example.laptopium.networkmanager.productdetails.model.Cart;
import com.example.laptopium.networkmanager.productdetails.model.MerchantProduct;
import com.example.laptopium.networkmanager.productdetails.model.ProductDetails;
import com.example.laptopium.networkmanager.productdetails.network.ICustomerApis;
import com.example.laptopium.networkmanager.productdetails.retrofitbuilder.RetrofitBuilder;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductDetailsActivity extends AppCompatActivity implements OtherMerchantsRVAdapter.IMerchant {
    private String imageUrl;
    private String productName;
    private double price;
    private int qty;
    private String pid;
    private String moreAttributes;
    private String merchantId;
    private int qtyfinal;
    private int quantity;
    Cart cart = new Cart();
    List<MerchantProduct> merchantProductList = new ArrayList<>();
    private List<Cart> cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setProductDetails();
        String str_prodcutId = getIntent().getStringExtra("productId");

        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        TextView et_qty = findViewById(R.id.tv_add_product_details_quantity);
        qty = Integer.parseInt(et_qty.getText().toString());
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Button btn = findViewById(R.id.btn_product_details_add_to_cart);
        String str = sharedPreferences.getString("isLoggedIn", "false");

        //if (str.equals("true")) {

            String userId = sharedPreferences.getString("userMail", null);
            if (userId != null) {
                int index = userId.indexOf("@");
                userId = userId.substring(0, index);
                cart.setUserId(userId);
            }
            //for guest
            else{
                if(sharedPreferences.getString("guest", null)==null) {
                    userId = generateGuest();
                    editor.putString("guest", userId);
                }
                else
                    userId = sharedPreferences.getString("guest", null);
                cart.setUserId(userId);
            }
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferences1 = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                    if (Integer.parseInt(et_qty.getText().toString()) <= quantity) {
                        btn.setEnabled(true);
                        cart.setQuantity(Integer.parseInt(et_qty.getText().toString()));
                        if (cart.getMerchantId() == null || cart.getProductId() == null || cart.getUserId() == null || cart.getQuantity() < 0 || cart.getProductName() == null) {
                            Intent intent = new Intent(ProductDetailsActivity.this, ErrorActivity.class);
                            startActivity(intent);
                        }
                        sendCart(cart);

                    } else {
                        Toast.makeText(ProductDetailsActivity.this, "Out Of Stock", Toast.LENGTH_SHORT).show();

                    }


//                Intent intent = new Intent(ProductDetailsActivity.this, MyCartActivity.class);
//                startActivity(intent);
                }
            });
//        } else {
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////
//
////                        cart.setQuantity(Integer.parseInt(et_qty.getText().toString()));
////                        cartList.add(cart);
////                        PrefConfig.writeListInPref(getApplicationContext(), cartList);
//
//                }
//            });
//        }


        Button minus = findViewById(R.id.btn_minusproduct_qty);
        Button add = findViewById(R.id.btn_addproduct_qty);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int qty = Integer.parseInt(et_qty.getText().toString());
                if (qty < quantity) {
                    qty++;
                    qtyfinal = qty;
                    et_qty.setText(String.valueOf(qty));
                } else {
                    Toast.makeText(ProductDetailsActivity.this, "Maximum quantity reached", Toast.LENGTH_SHORT).show();

                }

            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(et_qty.getText().toString());
                if (qty <= 1) {
                    Toast.makeText(ProductDetailsActivity.this, "Minimum Quantity", Toast.LENGTH_SHORT).show();
                } else {
                    qty--;

                    qtyfinal = qty;
                    et_qty.setText(String.valueOf(qty));

                }


            }
        });


    }


    private void sendCart(Cart cart) {
//        if(merchantId.equals(null))
//                   merchantId="default";
        Retrofit retrofit = RetrofitBuilder.getInstance();
        ICustomerApis iCustomerApis = retrofit.create(ICustomerApis.class);
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        if (sharedPreferences.getString("userMail", null) != null || sharedPreferences.getString("guest", null) != null) {


            Call<Cart> cartDetails = iCustomerApis.sendCartDetails(cart);

            Callback<Cart> callback = new Callback<Cart>() {
                @Override
                public void onResponse(Call<Cart> call, Response<Cart> response) {


                    Intent intent = new Intent(ProductDetailsActivity.this, MyCartActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Cart> call, Throwable t) {
                    Toast.makeText(ProductDetailsActivity.this, "Unable to add cart.", Toast.LENGTH_SHORT).show();
                }
            };

            cartDetails.enqueue(callback);
        }
//        } else {
//            Toast.makeText(ProductDetailsActivity.this, "Login to add to cart.", Toast.LENGTH_SHORT).show();
//        }

    }


    // Display specific product detials
    private void setProductDetails() {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        ICustomerApis iCustomerApis = retrofit.create(ICustomerApis.class);
        String str_prodcutId = getIntent().getStringExtra("productId");
        Call<ProductDetails> productDetail = iCustomerApis.getProductDetails(str_prodcutId);
        Callback<ProductDetails> callback = new Callback<ProductDetails>() {
            @Override
            public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {
                imageUrl = response.body().getImageUrl();
                cart.setProductImageUrl(imageUrl);
                productName = response.body().getProduct();
                cart.setProductName(productName);
                merchantId = response.body().getMerchantId();
                quantity = response.body().getQuantity();
                cart.setMerchantId(merchantId);
                //Toast.makeText(ProductDetailsActivity.this, ""+merchantId, Toast.LENGTH_SHORT).show();
                pid = response.body().getId();
                cart.setProductId(pid);
                price = response.body().getPrice();
                cart.setPrice(price);
                moreAttributes = response.body().toString();
                displayAllMerchant();
                TextView tv_product_name = findViewById(R.id.tv_product_details_product_name);
                TextView tv_product_rating = findViewById(R.id.tv_product_details_product_merchant_rating);
                TextView tv_product_category = findViewById(R.id.tv_product_details_category);
                TextView tv_product_price = findViewById(R.id.tv_product_details_price);
                TextView tv_product_type = findViewById(R.id.tv_product_details_typeName);
                TextView tv_product_attributes = findViewById(R.id.tv_product_details_more_attributes);
                ImageView iv_product_image = findViewById(R.id.iv_product_details_product_image);
                tv_product_name.setText(response.body().getProduct().toString());
                tv_product_rating.setText("Rating : " + String.valueOf(response.body().getMerchantProductRating()));
                tv_product_type.setText("Type : " + response.body().getTypeName().toString());
                tv_product_category.setText("Category : " + response.body().getCompany().toString());
                tv_product_price.setText("Price : " + String.valueOf(response.body().getPrice()));

                tv_product_attributes.setText("\nMore Attributes : \n" + response.body().toString());
                Glide.with(ProductDetailsActivity.this)
                        .load(response.body().getImageUrl())
                        .into(iv_product_image);
            }

            @Override
            public void onFailure(Call<ProductDetails> call, Throwable t) {
                Toast.makeText(ProductDetailsActivity.this, "Couldn't load product.", Toast.LENGTH_SHORT).show();
            }
        };
        productDetail.enqueue(callback);
    }


    private void displayAllMerchant() {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        ICustomerApis iCustomerApis = retrofit.create(ICustomerApis.class);
        Call<List<MerchantProduct>> merchantsOfProductLists = iCustomerApis.findMerchantsOfProduct(pid);
        Callback<List<MerchantProduct>> callback = new Callback<List<MerchantProduct>>() {
            @Override
            public void onResponse(Call<List<MerchantProduct>> call, Response<List<MerchantProduct>> response) {
                for (MerchantProduct merchantProduct : response.body()) {
                    merchantProductList.add(merchantProduct);
                }
                RecyclerView recyclerView = findViewById(R.id.rcv_product_details__show_merchants);
                OtherMerchantsRVAdapter otherMerchantsRVAdapter = new OtherMerchantsRVAdapter(merchantProductList, ProductDetailsActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(ProductDetailsActivity.this));
                recyclerView.setAdapter(otherMerchantsRVAdapter);
            }

            @Override
            public void onFailure(Call<List<MerchantProduct>> call, Throwable t) {
                Toast.makeText(ProductDetailsActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        };
        merchantsOfProductLists.enqueue(callback);

    }

    @Override
    public void onUserClick1(MerchantProduct merchantProduct) {

        Toast.makeText(this, "xyzzzzz", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ProductDetailsActivity.this, ProductDetailsActivity.class);
        intent.putExtra("productId", merchantProduct.getProductId());
        Log.d("productscroll123", "onUserClick: " + merchantProduct.getMerchantId());
        startActivity(intent);
    }


    String generateGuest() {
        String uniqueID = UUID.randomUUID().toString();
        Log.d("tiwariii", "generateGuest: " + uniqueID);
        return uniqueID;
    }
}
