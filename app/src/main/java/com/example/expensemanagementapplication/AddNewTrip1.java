package com.example.expensemanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

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
/*
        fName = firstName.getText().toString().trim();
        lName = lastName.getText().toString().trim();
        empId = employeeId.getText().toString().trim();

        if(fName.length()>0 && lName.length()>0 && empId.length()>0){
            boolean userExists = checkExistingUser(empId);
            if(userExists){
                raiseToast("Entered User already Exists");
            }
            else{
                moveToSignUp1();
            }
        }
        else{
            raiseToast("Enter details");
        }*/
    }
}