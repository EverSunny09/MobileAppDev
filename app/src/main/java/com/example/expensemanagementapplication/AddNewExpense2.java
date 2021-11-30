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
        getPreviousValues();
        putExpTypes();
        putcurrTypes();
        selectDateTime();
    }

    public void getPreviousValues(){
        Intent i = getIntent();
        excComm = i.getStringExtra(expComm);
        expAmtt = i.getStringExtra(expAmt);
        expTypee = i.getStringExtra(expType);
        expCurrr = i.getStringExtra(expCurr);
        otherTypee = i.getStringExtra(otherType);
    }

    public void addExp(View view){
        ExpenseModel expModel = new ExpenseModel(-1,1,expTypes.get(expType), currTypes.get(expCurr), expDateTime, expComm, Integer.parseInt(expAmt), otherType);
        saveExpToDb(expModel);
    }

    private void putExpTypes(){
        expTypes.put("Food",1);
        expTypes.put("Accommodation",2);
        expTypes.put("Travel",3);
    }

    private void putcurrTypes(){
        currTypes.put("GBP",1);
        currTypes.put("INR",2);
        currTypes.put("EU",3);
        currTypes.put("USD",4);
        currTypes.put("CAD",5);
        currTypes.put("AUD",6);
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

    private void raiseToast(String toastMsg){
        Toast alertText = Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT);
        alertText.show();
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