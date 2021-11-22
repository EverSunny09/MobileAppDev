package com.example.expensemanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddNewTrip2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_trip2);
    }
    public void nextButtonClick(View view){
        Intent i = new Intent(AddNewTrip2.this,AddNewTrip3.class);
        startActivity(i);
    }
}