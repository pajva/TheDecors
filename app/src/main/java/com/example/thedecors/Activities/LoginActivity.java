package com.example.thedecors.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.thedecors.R;

public class LoginActivity extends AppCompatActivity {

    private EditText et1,et2;
    private TextView tv1,tv2;
    private Button bt1;
    private String enteredUsername,enteredPassword;

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
                if (validation()){
                    registration();
                }
            }
        });
    }

    private boolean validation() {
        Boolean status = true;
        // Get user input
        enteredUsername = et1.getText().toString();
        enteredPassword = et2.getText().toString();
        return status;
    }

    private void registration() {
    }
}