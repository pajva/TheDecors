package com.example.thedecors.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thedecors.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText et1,et2,et3,et4,et5;
    private Button bt1;
    private SharedPreferences sharedPreferences;
    private String enteredUsername,enteredPhoneNumber,enteredEmailID,enteredPassword,enteredConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Registration");
        et1 = findViewById(R.id.etName);
        et2 = findViewById(R.id.etPhone);
        et3 = findViewById(R.id.et_EmailAddress);
        et4 = findViewById(R.id.et_Password);
        et5 = findViewById(R.id.et_RetypePass);
        bt1 = findViewById(R.id.btRegister);

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
        boolean status = true;
        // Get user input
        enteredUsername = et1.getText().toString();
        enteredPhoneNumber = et2.getText().toString();
        enteredEmailID = et3.getText().toString();
        enteredPassword = et4.getText().toString();
        enteredConfirmPassword = et5.getText().toString();

        if (enteredUsername.isEmpty()){
            et1.setError("Field is Empty");
            et1.requestFocus();
            status=false;
        }
        else if (enteredPhoneNumber.isEmpty()) {
            et2.setError("Field is empty");
            et2.requestFocus();
            status=false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(enteredEmailID).matches()) {
            et3.setError("Enter a valid email ID");
            et3.requestFocus();
            status=false;
        }
        else if (enteredPassword.isEmpty()) {
            et4.setError("Field is Empty");
            et4.requestFocus();
            status=false;
        }
        else if (enteredPassword.length() < 6) {
            et4.setError("Length of the password should be more than 6");
            et4.requestFocus();
            status=false;
        }
        else if (enteredConfirmPassword.isEmpty()) {
            et5.setError("Field is Empty");
            et5.requestFocus();
            status=false;
        }
        else if (enteredConfirmPassword.length() < 6) {
            et5.setError("Length of the password should be more than 6");
            et5.requestFocus();
            status=false;
        }
        else if (!enteredPassword.equals(enteredConfirmPassword)) {
            et5.setError("Password not matching!");
            et5.requestFocus();
            status=false;
        }
        return status;
    }

    private void registration() {

        // Initialize SharedPreferences & Store user data in SharedPreferences
        sharedPreferences = getSharedPreferences(enteredPhoneNumber, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", enteredPhoneNumber);
        editor.putString("password", enteredConfirmPassword);
        editor.apply();

        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
        finish(); // Finish the SignUpActivity and go back to the login screen
    }
}