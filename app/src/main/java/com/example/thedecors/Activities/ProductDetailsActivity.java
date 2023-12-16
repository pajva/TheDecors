package com.example.thedecors.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        setTitle("Product Details");
        ivpd1 = findViewById(R.id.ivd1);
        tvpd1 = findViewById(R.id.tvd1);

        Intent intent = getIntent();
        if (intent != null) {
            int position = intent.getIntExtra("position", -1);
            String itemName = intent.getStringExtra("itemName");
            int itemID = intent.getIntExtra("itemID", -1);
            Picasso.get()
                    .load(itemID)
                    .placeholder(R.drawable.baseline_nothing_24) // Placeholder image while loading
                    .error(R.drawable.baseline_error_outline_24) // Error image if loading fails
                    .into(ivpd1);
            tvpd1.setText(itemName);

        }

        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // Handle the new rating
                Toast.makeText(ProductDetailsActivity.this, "Rating: " + rating, Toast.LENGTH_SHORT).show();
            }
        });

    }
}