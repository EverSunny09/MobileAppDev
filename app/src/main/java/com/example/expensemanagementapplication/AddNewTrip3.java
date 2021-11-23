package com.example.expensemanagementapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
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

    TextView startDateText,endDateText;
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
            }
        });
        materialDatePicker.addOnNegativeButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void raiseToast(String toastMsg){
        Toast alertText = Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT);
        alertText.show();
    }
}

