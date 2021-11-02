package com.example.expensemanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

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
            boolean validEmail = validateEmail();
            boolean validPassword =validatePassword(pwd,conPwd);
            if(validEmail && validPassword){

                // encrypt pwd
                //add user in db
                Intent homeScreen = new Intent(this, HomeScreen.class);
                homeScreen.putExtra(HomeScreen.user,firstName);
                startActivity(homeScreen);

            }
        }

    }

    private  boolean validatePassword(String pwd,String conPwd){
        if(pwd.equals(conPwd))
            return true;
        else{
            Toast alertText = Toast.makeText(getApplicationContext(), "Please check your password", Toast.LENGTH_SHORT);
            alertText.show();
            return false;
        }

    }

    private boolean validateEmail(){
        String emailInput = emailOrPhone.getText().toString().trim();
        if(emailInput.isEmpty()){
            Toast alertText = Toast.makeText(getApplicationContext(), "Please enter valid Email Address", Toast.LENGTH_SHORT);
            alertText.show();
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            Toast alertText = Toast.makeText(getApplicationContext(), "Please enter valid Email Address", Toast.LENGTH_SHORT);
            alertText.show();
            return false;
        }
        else{
            return true;
        }
    }

}