package com.example.expensemanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Network;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class AddNewTrip2 extends AppCompatActivity {

    EditText destination;
    Switch isInternational,requiredAsses;
    TripModel tripModel = new TripModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_trip2);

        getPreviousActivityValue();
        getValuesFromComponents();
        setDefaultValues();
        setIsInternationCheckListener();
        setRequiredAssessCheckListener();

    }

    private void getValuesFromComponents() {
        destination = (EditText)findViewById(R.id.destination);
        isInternational = (Switch) findViewById(R.id.isInternational);
        requiredAsses = (Switch) findViewById(R.id.isRiskAssessmentRequired);
    }

    private void getPreviousActivityValue() {
        TripModel trpModel = getIntent().getParcelableExtra("tripmodel");
        tripModel = trpModel;
    }

    private void setDefaultValues() {
        tripModel.setIsInternationalTrip(2);
        tripModel.setRequireRiskAssessment(2);
    }

    private void setRequiredAssessCheckListener() {
        requiredAsses.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setRiskAssessRequired(isChecked);
            }
        });
    }

    private void setIsInternationCheckListener() {
        isInternational.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setIsInternational(isChecked);
            }
        });
    }


    public void setRiskAssessRequired(boolean isChecked) {
        if(isChecked)
            tripModel.setRequireRiskAssessment(1);
        else
            tripModel.setRequireRiskAssessment(2);
    }

    public void setIsInternational(boolean isChecked) {
        if(isChecked)
            tripModel.setIsInternationalTrip(1);
        else
            tripModel.setIsInternationalTrip(2);
    }

    public void nextButtonClick(View view){
        boolean isValid = validateForm();
        if(isValid){
            moveToNext();
        }
    }
    private void previousButtonClick(View view){
        finish();
    }

    private void moveToNext(){
        Intent i = new Intent(AddNewTrip2.this,AddNewTrip3.class);
        i.putExtra("tripmodel",tripModel);
        startActivity(i);
    }
    private boolean validateForm() {
        setTripValues();
        if(tripModel.getDestination().length()>0){
            return true;
        }
        else{
            raiseToast("Please Enter Details");
            return false;
        }
    }
    private void setTripValues(){
        tripModel.setDestination(destination.getText().toString().trim());
    }
    private void raiseToast(String toastMsg){
        Toast alertText = Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT);
        alertText.show();
    }

}