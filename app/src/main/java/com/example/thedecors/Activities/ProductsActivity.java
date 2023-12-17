package com.example.thedecors.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.thedecors.Adapters.ItemAdapter;
import com.example.thedecors.Interfaces.OnItemClickListener;
import com.example.thedecors.Models.ItemModel;
import com.example.thedecors.R;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity implements OnItemClickListener {

    SharedPreferences sharedPreferences;
    RecyclerView recyclerView;
    String logUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        setTitle("Products");

        Intent intent = getIntent();
        logUser = intent.getStringExtra("loggedUser");
        sharedPreferences = getSharedPreferences("Person", Context.MODE_PRIVATE);
        if (logUser!=null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("loggedUser",logUser);
            editor.apply();
        }
        logUser = sharedPreferences.getString("loggedUser", "");
        Log.e("PRODUCTSUSERshared", sharedPreferences.getString("loggedUser", ""));
        Log.e("PRODUCTSUSER", logUser);


        // Create a list of items
        List<ItemModel> itemList = new ArrayList<>();
        itemList.add(new ItemModel(R.drawable.patio,"Garden Table"));
        itemList.add(new ItemModel(R.drawable.bedcover,"King Size Bed Cover"));

        // Set up RecyclerView
        recyclerView = findViewById(R.id.rv1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemAdapter itemAdapter = new ItemAdapter(itemList,this);
        recyclerView.setAdapter(itemAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_logout) {
            Toast.makeText(this, "Logged out Successfully", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
            sharedPreferences = getSharedPreferences("Common", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("status", false);
            editor.apply();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int position,ItemModel item) {
        // Handle item click, for example, start a new activity with item details
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("itemName", item.getItemName());
        intent.putExtra("itemID", item.getItemId());
        intent.putExtra("loggedUser", logUser);
        startActivity(intent);
    }
}