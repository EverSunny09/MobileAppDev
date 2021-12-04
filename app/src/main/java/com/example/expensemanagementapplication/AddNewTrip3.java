package com.example.expensemanagementapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNewTrip3 extends AppCompatActivity {

    TextView startDateText,endDateText,tripName,tripDesc,tripType,tripDest,tripIsInternational,tripRiskAssessmentNeeded;
    TripModel tripModel = new TripModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_trip3);
        getPreviousActivityValue();
        getValuesFromComponents();
        setDatePickerControl();

    }

    private void getValuesFromComponents() {
        tripName= findViewById(R.id.tripName);
        tripDesc= findViewById(R.id.tripDesc);
        tripDest= findViewById(R.id.tripDest);
        tripType= findViewById(R.id.tripType);
        tripIsInternational= findViewById(R.id.isInternational);
        tripRiskAssessmentNeeded= findViewById(R.id.isRiskAssessmentRequired);
        startDateText = findViewById(R.id.startDateText);
        endDateText = findViewById(R.id.endDateText);
    }

    private void getPreviousActivityValue() {
        TripModel trpModel = getIntent().getParcelableExtra("tripmodel");
        tripModel = trpModel;
    }

    private void setDatePickerControl(){
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.dateRangePicker();
        final MaterialDatePicker materialDatePicker = builder.build();
        materialDatePicker.show(getSupportFragmentManager(), materialDatePicker.toString());
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                String selectedDate = selection.toString();
                androidx.core.util.Pair selectedDates = (androidx.core.util.Pair) materialDatePicker.getSelection();
                final Pair<Date, Date> rangeDate = new Pair<>(new Date((Long) selectedDates.first), new Date((Long) selectedDates.second));
                Date startDate = rangeDate.first;
                Date endDate = rangeDate.second;
                SimpleDateFormat simpleFormat = new SimpleDateFormat("dd MMM yyyy");
                startDateText.setText("Start Date: " + simpleFormat.format(startDate));
                endDateText.setText("Start Date: " + simpleFormat.format(endDate));
                setTripValues((Long) selectedDates.first,(Long) selectedDates.second);
            }
        });
        materialDatePicker.addOnNegativeButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setTripValues(Long startDate,Long endDate){
        tripName.setText("Trip Name : " +tripModel.getTripName());
        tripDesc.setText("Description : "+tripModel.getDescription());
        tripType.setText("Type : "+(tripModel.getTypeOfTrip() == "Others" ? tripModel.getOtherType() : tripModel.getTypeOfTrip()));
        tripDest.setText("Destination : "+tripModel.getDestination());
        tripIsInternational.setText("Is International : "+ (tripModel.getIsInternationalTrip() == 1 ? "Yes" : "No"));
        tripRiskAssessmentNeeded.setText("Risk Assessment Needed : "+(tripModel.getRequireRiskAssessment() ==1 ? "Yes" : "No"));
        tripModel.setTripStartDate(startDate);
        tripModel.setTripEndDate(endDate);
        tripModel.setUserId(getLoggedInUserId());
        tripModel.setIsActive(1);
    }

    private int getLoggedInUserId() {
        SessionManagement sessionManagement=new SessionManagement(AddNewTrip3.this);
        return sessionManagement.getSession();
    }

    private void raiseToast(String toastMsg){
        Toast alertText = Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT);
        alertText.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onDiscardButtonClicked(View view){
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void moveToHomeScreen() {
        Intent homeScreen = new Intent(this, HomeScreen.class);
        Bundle b = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        homeScreen.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(homeScreen,b);
        raiseToast("Trip added Successfully! ");
    }

    private void moveToAllTrips(){
        Intent allTrips = new Intent(this, AllTripsListView.class);
        startActivity(allTrips);
        raiseToast("Trip added Successfully! ");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onSaveButtonClicked(View view){
        boolean successfullyAddedTrip =  saveDataIntoDB();
        if(!successfullyAddedTrip)
            raiseToast("Please try again! ");
        else{
            moveToAllTrips();
        }

    }

    private boolean saveDataIntoDB() {
        DataBaseExecution dataBaseExecution = new DataBaseExecution(this);
        return dataBaseExecution.addNewTrip(tripModel);
    }
}

