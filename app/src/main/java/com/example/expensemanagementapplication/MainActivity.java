package com.example.expensemanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText emailOrNo;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void logInButtonClick(View view){

        emailOrNo = (EditText)findViewById(R.id.userName);
        password = (EditText)findViewById(R.id.password);

        String user = emailOrNo.getText().toString();

        // db validation for existing user and password

        Intent logIn = new Intent(this, LogIn.class);
        logIn.putExtra(LogIn.userName,user);
        startActivity(logIn);

    }

    public void signupButtonClick(View view){

        Intent signUp = new Intent(this, SignUp.class);
        startActivity(signUp);

    }

}