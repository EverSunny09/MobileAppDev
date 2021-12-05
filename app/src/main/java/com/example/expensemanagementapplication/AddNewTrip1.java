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
    String tripId;
    Boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_trip1);

        getValuesFromComponents();
        setSpinnerListener();
        checkIfIsEdit();
    }

    private void checkIfIsEdit(){
        Intent i = getIntent();
        tripId = i.getStringExtra("tripId");
        if(tripId!=null){
            DataBaseExecution db = new DataBaseExecution(this);
            Cursor trip = db.getData("*","trip","trip_id",tripId);
            trip.moveToNext();
            tripModel.setTripId(trip.getInt(0));
            tripModel.setTripName(trip.getString(2));
            tripModel.setDestination(trip.getString(3));
            tripModel.setTripStartDate(trip.getLong(4));
            tripModel.setTripEndDate(trip.getLong(5));
            tripModel.setRequireRiskAssessment(trip.getInt(6));
            tripModel.setDescription(trip.getString(7));
            tripModel.setIsActive(trip.getInt(8));
            tripModel.setTypeOfTrip(trip.getString(9));
            tripModel.setTotalCompensated(trip.getString(11));
            tripModel.setTotalExpense(trip.getString(12));
            tripModel.setIsInternationalTrip(trip.getInt(13));
            convertIntoFieldValues(tripModel);
        }
    }

    private void convertIntoFieldValues(TripModel tripModel){
        tripName.setText(tripModel.getTripName());
        tripDescription.setText(tripModel.getDescription());
        tripType.setSelection(((ArrayAdapter)tripType.getAdapter()).getPosition(tripModel.getTypeOfTrip()));
    }

    private void setSpinnerListener() {
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

    private void getValuesFromComponents() {
        tripName = (EditText)findViewById(R.id.tripName);
        tripDescription = (EditText)findViewById(R.id.tripDesc);
        otherType = (EditText)findViewById(R.id.otherType);
        tripType = (Spinner) findViewById(R.id.tripType);
        tripModel.setTypeOfTrip("Meeting");
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
        tripModel.setTypeOfTrip(tripType.getItemAtPosition(position).toString());
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