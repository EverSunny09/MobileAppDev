package com.example.expensemanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

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

        //getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
    }

    private ViewGroup containerView;


}