package com.example.thedecors.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thedecors.R;

public class LoginActivity extends AppCompatActivity {

    private EditText et1,et2;
    private TextView tv1,tv2;
    private Button bt1;
    private String enteredUsername,enteredPassword;
    private SharedPreferences sharedPreferences,sharedPreferencesCommon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");
        et1 = findViewById(R.id.et_EmailLogin);
        et2 = findViewById(R.id.et_PasswordLogin);
        tv1 = findViewById(R.id.tvForgotPassword);
        tv2 = findViewById(R.id.tvNewAccount);
        bt1 = findViewById(R.id.btLogin);

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
            }
        });
    }

    private void validation() {
        Boolean status = true;
        // Get user input
        enteredUsername = et1.getText().toString();
        enteredPassword = et2.getText().toString();

        if (enteredUsername.isEmpty()){
            et1.setError("Field is Empty");
            et1.requestFocus();
            status=false;
        }
        else if (enteredPassword.isEmpty()){
            et2.setError("Field is Empty");
            et2.requestFocus();
            status=false;
        }

        // Check if the SharedPreferences with the specified name is present
        if (isSharedPreferencesPresent(enteredUsername) && status == true) {
            // The SharedPreferences is present
            login();
        } else {
            // The SharedPreferences is not present
            Toast.makeText(this, "User not Found", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isSharedPreferencesPresent(String preferenceName) {
        SharedPreferences sharedPreferences = getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        return sharedPreferences.getAll().size() > 0;
    }

    private void login() {

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(enteredUsername, Context.MODE_PRIVATE);

        // Retrieve stored user data from SharedPreferences
        String storedUsername = sharedPreferences.getString("username", "");
        String storedPassword = sharedPreferences.getString("password", "");

        // Check if entered credentials match stored credentials
        if (enteredUsername.equals(storedUsername) && enteredPassword.equals(storedPassword)) {
            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
            // Redirect or perform other actions after successful login

            sharedPreferencesCommon = getSharedPreferences("Common",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferencesCommon.edit();
            editor.putBoolean("status", true);
            editor.apply();

            // For example, you can start a new activity:
            Intent intent = new Intent(this, ProductsActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
}