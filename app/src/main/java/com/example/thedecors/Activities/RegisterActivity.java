package com.example.thedecors.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.thedecors.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText et1,et2,et3,et4,et5;
    private Button bt1;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et1 = findViewById(R.id.etName);
        et2 = findViewById(R.id.etPhone);
        et3 = findViewById(R.id.et_EmailAddress);
        et4 = findViewById(R.id.et_Password);
        et5 = findViewById(R.id.et_RetypePass);
        bt1 = findViewById(R.id.btRegister);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("DecorCredentials", Context.MODE_PRIVATE);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        // Get user input
        String enteredUsername = et1.getText().toString();
        String enteredPhoneNumber = et2.getText().toString();
        String enteredEmailID = et3.getText().toString();
        String enteredPassword = et4.getText().toString();
        String enteredConfirmPassword = et5.getText().toString();

        if (enteredUsername.isEmpty()){
            et1.setError("Name is Empty");
            et1.requestFocus();
        }
        if (enteredPhoneNumber.isEmpty()) {
            et2.setError("Phone number is empty");
            et2.requestFocus();
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(enteredEmailID).matches()) {
            et3.setError("Enter the valid email address");
            et3.requestFocus();
        }
        if (enteredPassword.isEmpty()) {
            et4.setError("Enter the password");
            et4.requestFocus();
        }
        else if (enteredPassword.length() < 6) {
            et4.setError("Length of the password should be more than 6");
            et4.requestFocus();
        }
        if (enteredConfirmPassword.isEmpty()) {
            et5.setError("Enter the password");
            et5.requestFocus();
        }
        else if (enteredConfirmPassword.length() < 6) {
            et5.setError("Length of the password should be more than 6");
            et5.requestFocus();
        }
        else if (!enteredPassword.equals(enteredConfirmPassword)) {
            et5.setError("Password not matching!");
            et5.requestFocus();
        }
    }
}