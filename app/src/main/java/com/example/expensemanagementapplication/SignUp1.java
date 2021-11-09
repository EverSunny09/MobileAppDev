package com.example.expensemanagementapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
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
    String emailPhone,pwd,conPwd,encryptedPass,decryptedPass,fName,lName,empId;
    private EditText emailOrPhone,password,confirmPassword;
    String AES="AES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up1);

        emailOrPhone = (EditText)findViewById(R.id.emailOrPhone);
        password = (EditText)findViewById(R.id.passwordSignUp);
        confirmPassword = (EditText)findViewById(R.id.confirmPwdSignUp);

        Intent i = getIntent();
        fName = i.getStringExtra(firstName);
        lName = i.getStringExtra(lastName);
        empId = i.getStringExtra(employeeId);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void signInButtonClick(View view){
        emailPhone = emailOrPhone.getText().toString().trim();
        pwd = password.getText().toString().trim();
        conPwd = confirmPassword.getText().toString().trim();

        if(emailPhone.length()>0 && pwd.length()>0 && conPwd.length()>0){
            boolean validEmail = validateEmail(emailPhone);
            boolean validPassword = validatePassword(pwd,conPwd);
            if(validEmail && validPassword){
                try{

                    encryptedPass = encrypt(pwd,emailPhone);
                    UserModel User = new UserModel(-1,fName,lName,encryptedPass,emailPhone,1,empId);
                    saveIntoDB(User);
                }
                catch (Exception e){
                    raiseToast("Something went wrong! Please try again.");
                }

            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void saveIntoDB(UserModel User){
        DataBaseExecution db = new DataBaseExecution(this);
        boolean output = db.addNewUser(User);
        if(output){
            raiseToast("User added successfully. Please Login");
            moveToHomePage();
        }

        else{
            raiseToast("Something went wrong! Please try again.");
        }
    }

    private void moveToLoginPage(){
        Intent logIn = new Intent(this, LogIn.class);
        startActivity(logIn);
    }


    public void decryptClick(View view){
        try{
            emailPhone = emailOrPhone.getText().toString().trim();
            decryptedPass = decrypt(encryptedPass,emailPhone);
        }
        catch (Exception e){

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

    public boolean validateEmail(String emailInput){
        if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            raiseToast("Please enter valid Email Address");
            return false;
        }
        else {
            return checkExistingEmail(emailInput);
        }
    }

    private boolean checkExistingEmail(String email){
        DataBaseExecution db = new DataBaseExecution(this);
        if(db.checkExistingUserEmail(email)){
            raiseToast("Email address already exists.");
            return false; //returning false since we are checking valid email
        }
        else{
            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void moveToHomePage(){
        Intent homeScreen = new Intent(this, HomeScreen.class);
        homeScreen.putExtra(HomeScreen.user,firstName);
        Bundle b = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        startActivity(homeScreen,b);
    }

    private void raiseToast(String toastMsg){
        Toast alertText = Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT);
        alertText.show();
    }

    private String decrypt(String password, String employeeId) throws Exception{
        SecretKeySpec key =generateSecretKey(employeeId);
        Cipher c= Cipher.getInstance(AES);
        c.init(Cipher.DECRYPT_MODE,key);
        byte[] decodedValue = Base64.decode(password,Base64.DEFAULT);
        byte[] decValue = c.doFinal(decodedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    private String encrypt(String password,String employeeId) throws Exception{
        SecretKeySpec key =generateSecretKey(employeeId);
        Cipher c= Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE,key);
        byte[] encValue = c.doFinal(password.getBytes());
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