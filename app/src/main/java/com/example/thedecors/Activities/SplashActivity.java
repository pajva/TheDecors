package com.example.thedecors.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.thedecors.R;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private final int SPLASH_DISPLAY_LENGTH = 1500;
    private ImageView imgIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferences = getSharedPreferences("Common", Context.MODE_PRIVATE);
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.zoom);
        imgIcon=findViewById(R.id.img1);
        imgIcon.startAnimation(shake);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                if(sharedPreferences.getBoolean("status", false) == false) {
                    Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(mainIntent);
                } else {
                    Intent mainIntent = new Intent(SplashActivity.this, ProductsActivity.class);
                    startActivity(mainIntent);
                }
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}