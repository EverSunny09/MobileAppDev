package com.example.expensemanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Intro_page extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_page);
        new Handler().postDelayed(new Runnable() {
            @Override
                    public void run () {
                Intent homeIntent = new Intent(Intro_page.this,Intro__page.class );
                startActivity(homeIntent);
                        finish();


            }


        } ,SPLASH_TIME_OUT);
    }
}