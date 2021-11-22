package com.example.expensemanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AddNewTrip1 extends AppCompatActivity {

    Spinner tripType ;
    private static String[] tripTypes = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_trip1);

        tripType = (Spinner) findViewById(R.id.tripType);
        tripType.setPrompt("Choose Trip Type");

    }

    public void nextButtonClick(View view){
        Intent i = new Intent(AddNewTrip1.this,AddNewTrip2.class);
        startActivity(i);
    }
}