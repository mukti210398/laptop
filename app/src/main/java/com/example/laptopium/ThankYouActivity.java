package com.example.laptopium;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ThankYouActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);
        ImageView iv_gif = findViewById(R.id.iv_thank_you);
        Glide.with(this).asGif()
                .load("https://media1.tenor.com/images/fbe2abc35de102670015793c4ee3b003/tenor.gif")
                .into(iv_gif);
    }
}