package com.example.expensemanagementapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

    private EditText emailOrNo;
    private EditText password;
    String AES="AES"; //common


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseExecution dataBase = new DataBaseExecution(MainActivity.this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void logInButtonClick(View view){

        emailOrNo = (EditText)findViewById(R.id.userName);
        password = (EditText)findViewById(R.id.password);

        String email = emailOrNo.getText().toString();
        String pwd = password.getText().toString();

        if(email.length()>0 && pwd.length()>0) {
            boolean validEmail = validateEmail(email);
            if (validEmail) {
                try {
                    String encryptedPass = encrypt(pwd, email);
                    String dbPwd = getPwdFromDb(email.toLowerCase());
                    boolean validPwd = checkPwd(encryptedPass,dbPwd);

                    if(validPwd){
                        moveToHomeScreen(email);
                    }
                } catch (Exception e) {
                    raiseToast("Please Enter Valid Credentials!");
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void moveToHomeScreen(String email) {
        Intent logIn = new Intent(this, HomeScreen.class);
        logIn.putExtra(LogIn.userName,email);
        Bundle b = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        startActivity(logIn,b);
    }

    private boolean checkPwd(String encryptedPass, String dbPwd) {
        return (encryptedPass.equals(dbPwd));
    }

    public boolean validateEmail(String emailInput){ //common
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
            return true; //returning true since user email exists in db
        }
        else{
            raiseToast("Email address doesn't exists.");
            return false; // email doesn't exist in db
        }
    }

    private void raiseToast(String toastMsg){ //common
        Toast alertText = Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT);
        alertText.show();
    }

    private String encrypt(String password,String email) throws Exception{ //common
        SecretKeySpec key =generateSecretKey(email);
        Cipher c= Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE,key);
        byte[] encValue = c.doFinal(password.getBytes());
        String encryptedValue = Base64.encodeToString(encValue,Base64.DEFAULT);
        return encryptedValue;
    }

    private SecretKeySpec generateSecretKey(String password) throws Exception{//common
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes,0,bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key,"AES");
        return secretKeySpec;
    }

    public void signupButtonClick(View view){
        Intent signUp = new Intent(this, SignUp.class);
        startActivity(signUp);
    }

    private String getPwdFromDb(String email){ //common
        DataBaseExecution db = new DataBaseExecution(this);
        return db.getPwdFromDB(email);
    }

}