package com.example.thedecors.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thedecors.R;
import com.squareup.picasso.Picasso;

public class ProductDetailsActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private ImageView ivpd1;
    private TextView tvpd1;
    private boolean isRated = false;
    public SharedPreferences sharedPreferences;
    private int position,itemID;
    private String itemName,logUser;
    private float ratingValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        setTitle("Product Details");
        ivpd1 = findViewById(R.id.ivd1);
        tvpd1 = findViewById(R.id.tvd1);
        ratingBar = findViewById(R.id.ratingBar);
        Intent intent = getIntent();
        if (intent != null) {
            position = intent.getIntExtra("position", -1);
            itemName = intent.getStringExtra("itemName");
            logUser = intent.getStringExtra("loggedUser");
            Log.e("PRODUCTDdetailsUSER", logUser);
            itemID = intent.getIntExtra("itemID", -1);
            Picasso.get()
                    .load(itemID)
                    .placeholder(R.drawable.baseline_nothing_24) // Placeholder image while loading
                    .error(R.drawable.baseline_error_outline_24) // Error image if loading fails
                    .into(ivpd1);
            tvpd1.setText(itemName);

        }
        sharedPreferences = getSharedPreferences(logUser, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(itemName)){
            isRated = true;
            Toast.makeText(this, "Already Rated, please rate another product", Toast.LENGTH_SHORT).show();
            // Retrieve the rating value from SharedPreferences
            ratingValue = sharedPreferences.getFloat(itemName, 0.0f); // 0.0f is the default value
            ratingBar.setRating(ratingValue);
            ratingBar.setIsIndicator(true);
        }


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // Handle the new rating
                if (!isRated) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putFloat(itemName,rating);
                    editor.apply();
                    // Handle the new rating
                    Toast.makeText(ProductDetailsActivity.this, "Rating: " + rating, Toast.LENGTH_SHORT).show();

                    // Disable the RatingBar
                    ratingBar.setIsIndicator(true);

                    // Update the flag
                    isRated = true;
                }
            }
        });

    }
}