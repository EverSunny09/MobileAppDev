package com.example.expensemanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SignUp1 extends AppCompatActivity {

    public static final String firstName ="f";
    public static final String lastName ="l";
    public static final String employeeId = "e";
    String emailPhone,pwd,conPwd,test;
    private EditText emailOrPhone,password,confirmPassword;
    private Button signInButton;
    String AES="AES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up1);

        emailOrPhone = (EditText)findViewById(R.id.emailOrPhone);
        password = (EditText)findViewById(R.id.passwordSignUp);
        confirmPassword = (EditText)findViewById(R.id.confirmPwdSignUp);
        signInButton= (Button)findViewById(R.id.signIn);
    }

    public void signInButtonClick(View view){
        emailPhone = emailOrPhone.getText().toString().trim();
        pwd = password.getText().toString().trim();
        conPwd = confirmPassword.getText().toString().trim();

        if(emailPhone.length()>0 && pwd.length()>0 && conPwd.length()>0){
            boolean validEmail = validateEmail(emailPhone);
            boolean validPassword = validatePassword(pwd,conPwd);
            if(validEmail && validPassword){
                try{
                    signInButton.setText(encrypt(employeeId,pwd));
                }
                catch (Exception e){

                }

                // encrypt pwd
                //add user in db

            }
        }

    }

    private  boolean validatePassword(String pwd,String conPwd){
        if(pwd.equals(conPwd))
            return true;
        else{
            raiseToast("Please check your password");
            return false;
        }

    }

    private boolean validateEmail(String emailInput){
        if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            raiseToast("Please enter valid Email Address");
            return false;
        }
        else{
            return true;
        }
    }

    private void moveToHomePage(){
        Intent homeScreen = new Intent(this, HomeScreen.class);
        homeScreen.putExtra(HomeScreen.user,firstName);
        startActivity(homeScreen);
    }

    private void raiseToast(String toastMsg){
        Toast alertText = Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT);
        alertText.show();
    }

    private String encrypt(String employeeId,String password) throws Exception{
        SecretKeySpec key =generateSecretKey(password);
        Cipher c= Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE,key);
        byte[] encValue = c.doFinal(employeeId.getBytes());
        String encryptedValue = Base64.encodeToString(encValue,Base64.DEFAULT);
        return encryptedValue;
    }

    private SecretKeySpec generateSecretKey(String password) throws Exception{
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes,0,bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key,"AES");
        return secretKeySpec;
    }
}