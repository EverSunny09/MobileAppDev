package com.example.expensemanagementapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public class HomeScreen extends AppCompatActivity {

    public static final String user = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void logOut(View view){
        SessionManagement sessionManagement=new SessionManagement(HomeScreen.this);
        sessionManagement.removeSession();
        moveToLogin();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void moveToLogin() {
        Intent logIn = new Intent(HomeScreen.this, MainActivity.class);
        logIn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(logIn);
    }
}