package com.example.expensemanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddNewTrip1 extends AppCompatActivity {

    EditText tripName, tripDescription, otherType;
    Spinner tripType ;
    TripModel tripModel = new TripModel();
    Boolean isOtherType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_trip1);

        tripName = (EditText)findViewById(R.id.tripName);
        tripDescription = (EditText)findViewById(R.id.tripDesc);
        otherType = (EditText)findViewById(R.id.otherType);
        tripType = (Spinner) findViewById(R.id.tripType);

        tripType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setOtherField(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //otherType.setVisibility(View.GONE);
            }
        });
    }

    private void setOtherField(int position){
        if(tripType.getItemAtPosition(position).equals("Others")){
            otherType.setVisibility(View.VISIBLE);
            isOtherType =true;
        }
        else{
            otherType.setVisibility(View.GONE);
            isOtherType=false;
        }
    }

    public void nextButtonClick(View view){
        boolean isValid = validateForm();
        if(isValid){
            moveToNext();
        }
    }
    private void moveToNext(){
        Intent i = new Intent(AddNewTrip1.this,AddNewTrip2.class);
        i.putExtra("tripmodel",tripModel);
        startActivity(i);
    }
    private boolean validateForm() {
        setTripValues();
        if(tripModel.getTripName().length()>0 && tripModel.getDescription().length()>0 && (isOtherType == true ? tripModel.getOtherType().length()>0 : true)){
            return true;
        }
        else{
            raiseToast("Please Enter Details");
            return false;
        }
    }

    private void setTripValues(){
        tripModel.setTripName(tripName.getText().toString().trim());
        tripModel.setDescription(tripDescription.getText().toString().trim());

        if(isOtherType)
            tripModel.setOtherType(otherType.getText().toString().trim());
        else
            tripModel.setOtherType("");
    }

    private void raiseToast(String toastMsg){
        Toast alertText = Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT);
        alertText.show();
    }
}