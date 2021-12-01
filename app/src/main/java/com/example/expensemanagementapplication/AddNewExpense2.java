package com.example.expensemanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    public static final String expComm = "abc";
    public static final String expAmt = "new expense";
    public static final String expType = "food";
    public static final String expCurr = "INR";
    public static final String otherType ="others";
    public long expDateTime;
    DatePicker inputDate;
    TimePicker inputTime;
    String excComm,expAmtt,expTypee,expCurrr,otherTypee;

    HashMap<String, Integer> expTypes = new HashMap<String, Integer>();
    HashMap<String, Integer> currTypes = new HashMap<String, Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_expense2);
        getPreviousActivityValue();
        selectDateTime();
    }

    private void getPreviousActivityValue() {
        ExpenseModel expModel = getIntent().getParcelableExtra("expenseModel");
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

    }

    private void selectDateTime(){
        inputDate = (DatePicker) findViewById(R.id.date);
        inputTime = (TimePicker) findViewById(R.id.time);

        Calendar calendar = new GregorianCalendar(inputDate.getYear(),
                inputDate.getMonth(),
                inputDate.getDayOfMonth(),
                inputTime.getCurrentHour(),
                inputTime.getCurrentMinute());

        ///time = calendar.getTimeInMillis();
       // alertDialog.dismiss();
    }
}