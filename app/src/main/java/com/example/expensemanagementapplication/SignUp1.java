package com.example.expensemanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUp1 extends AppCompatActivity {

    public static final String firstName ="f";
    public static final String lastName ="l";
    public static final String employeeId = "e";

    private EditText emailOrPhone;
    private EditText password;
    private EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up1);

        emailOrPhone = (EditText)findViewById(R.id.emailOrPhone);
        password = (EditText)findViewById(R.id.passwordSignUp);
        confirmPassword = (EditText)findViewById(R.id.confirmPwdSignUp);

    }

    public void signInButtonClick(View view){
        String emailPhone = emailOrPhone.getText().toString();
        String pwd = password.getText().toString();
        String conPwd = confirmPassword.getText().toString();

        if(emailPhone!=null && pwd!=null && conPwd!=null){

            if(pwd==conPwd){

                // encrypt pwd
                //add user in db
                Intent homeScreen = new Intent(this, HomeScreen.class);
                homeScreen.putExtra(HomeScreen.user,firstName);
                startActivity(homeScreen);

            }
        }

    }

}