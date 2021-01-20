//package com.example.laptopium.networkmanager.checkout;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.laptopium.R;
//import com.example.laptopium.networkmanager.checkout.adapter.CheckOutAdapter;
//import com.example.laptopium.networkmanager.checkout.model.MyCart;
//import com.example.laptopium.networkmanager.checkout.network.ICheckoutApis;
//import com.example.laptopium.networkmanager.checkout.retrofitbuilder.RetrofitBuilder;
//
//import retrofit2.Retrofit;
//
//public class CheckoutActivity extends AppCompatActivity implements CheckOutAdapter.ICheckOut {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_checkout);
//        displayCart();
//    }
//
//
//    private void displayCart() {
//        SharedPreferences sharedPreferenes = getSharedPreferences("com.example.laptopium", MODE_PRIVATE);
//        Retrofit retrofit = RetrofitBuilder.getInstance();
//        String userMail = sharedPreferenes.getString("userMail", "test");
//        ICheckoutApis iCheckoutApis = retrofit.create(ICheckoutApis.class);
//
//        int price = Integer.parseInt(getIntent().getStringExtra("price"));
//
////            int index = userMail.indexOf("@");
////            String userId = userMail.substring(0, index);
//
//        //Call<List<MyCart>> myCartList = iCheckoutApis.cartDetailsOfUser(userMail);
////            Callback<List<ProductList>> callback = new Callback<List<ProductList>>() {
////                @Override
////                public void onResponse(Call<List<ProductList>> call, Response<List<ProductList>> response) {
////                    //Toast.makeText(ProductScroll.this, "Yayyy", Toast.LENGTH_SHORT).show();
////                    for (ProductList product : response.body()) {
////                        productLists.add(product);
////                    }
////                    //Toast.makeText(ProductScroll.this, ""+productLists.toString(), Toast.LENGTH_SHORT).show();
////                    RecyclerView recyclerView = findViewById(R.id.rv_productlist);
////                    ProductListRVAdapter productListRVAdapter = new ProductListRVAdapter(productLists, ProductScroll.this);
////                    recyclerView.setLayoutManager(new LinearLayoutManager(ProductScroll.this));
////                    recyclerView.setAdapter(productListRVAdapter);
////                }
////
////                @Override
////                public void onFailure(Call<List<ProductList>> call, Throwable t) {
////                    Toast.makeText(ProductScroll.this, "sdfghjk", Toast.LENGTH_SHORT).show();
////                }
////            };
////            productList.enqueue(callback);
////        }
////        else{
////            Toast.makeText(thisis, "Please select category", Toast.LENGTH_SHORT).show();
////        }
////    }
//
//
////    @Override
////    public void onUserClick(MyCart myCart) {
////        Toast.makeText(this, "Order Placed.", Toast.LENGTH_SHORT).show();
////        Intent intent = new Intent(CheckoutActivity.this, CategoryActivity.class);
////        //intent.putExtra("productId",product.getId());
////        //Log.d("productscroll123", "onUserClick: " + product.getMerchantId());
////        //intent.putExtra("product", new ProductList(product.getProduct(),product.getTypeName(),product.getMerchantId(), (float) product.getPrice(),product.getImageUrl(),product.getCompany(),product.getId()));
////        startActivity(intent);
////    }
//    }
//
//    @Override
//    public void onUserClick(MyCart myCart) {
//
//    }
//}