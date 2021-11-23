package com.example.expensemanagementapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

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
        startDateText = findViewById(R.id.startDateText);
        endDateText = findViewById(R.id.endDateText);

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


    }

    public void showDatePickerDialog(View v) {

    }
}

