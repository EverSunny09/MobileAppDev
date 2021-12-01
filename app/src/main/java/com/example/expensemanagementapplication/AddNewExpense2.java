package com.example.expensemanagementapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class AddNewExpense2 extends AppCompatActivity {

    ExpenseModel expenseModel = new ExpenseModel();
    DatePicker inputDate;
    Long time;
    TimePicker inputTime;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_expense2);
        getPreviousActivityValue();
        selectDateTime();
    }

    private void getPreviousActivityValue() {
        ExpenseModel expModel = getIntent().getParcelableExtra("model");
        expenseModel = expModel;
    }

    private void saveExpToDb(ExpenseModel expDBModel){
        DataBaseExecution db = new DataBaseExecution(this);
        boolean output = db.addNewExpense(expDBModel);
        if(output){
            raiseToast("User added successfully. Please Login");
            //move to trip details with all expense
        }

        else{
            raiseToast("Something went wrong! Please try again.");
        }
    }

    public void previousButtonClick(View view){
        finish();
    }

    private void raiseToast(String toastMsg){
        Toast alertText = Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT);
        alertText.show();
    }

    public void onSaveButtonClicked(View view){
        boolean successfullyAddedTrip =  saveDataIntoDB();
        if(!successfullyAddedTrip)
            raiseToast("Please try again! ");
        else{
            moveToHomePage();
        }
    }

    private void moveToHomePage(){
        Intent homeScreen = new Intent(this, HomeScreen.class);
        homeScreen.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(homeScreen);
        raiseToast("Expense added Successfully! ");
    }

    public void setDateAndTime(){
        inputDate = (DatePicker) findViewById(R.id.date);
        inputTime = (TimePicker) findViewById(R.id.time);

        Calendar calendar = new GregorianCalendar(inputDate.getYear(),
                inputDate.getMonth(),
                inputDate.getDayOfMonth(),
                inputTime.getCurrentHour(),
                inputTime.getCurrentMinute());

        time = calendar.getTimeInMillis();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void selectDateTime(){
        setDateAndTime();
        setDateChangeListener();
        setTimeChangeListener();
    }

    private void setTimeChangeListener(){
        inputTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                setDateAndTime();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setDateChangeListener(){
        inputDate.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                setDateAndTime();
            }
        });
    }
    private boolean saveDataIntoDB() {
        expenseModel.setExpenseDateTime(time);
        DataBaseExecution dataBaseExecution = new DataBaseExecution(this);
        return dataBaseExecution.addNewExpense(expenseModel);
    }
}