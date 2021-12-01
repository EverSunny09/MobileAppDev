package com.example.expensemanagementapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class AddNewExpense1 extends AppCompatActivity {

    EditText expenseComments, expenseAmount, otherType;
    Spinner expenseType, expenseCurrency;
    ExpenseModel expenseModel = new ExpenseModel();
    Boolean isOtherType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_expense1);
        getValuesFromComponents();
        setSpinnerListener();

    }

    private void setExpenseCurrencyListener(){
        expenseCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                expenseModel.setCurrency(expenseCurrency.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void setExpenseTypeListener(){
        expenseType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(expenseType.getItemAtPosition(position).equals("Others")){
                    otherType.setVisibility(View.VISIBLE);
                    isOtherType =true;
                }
                else{
                    otherType.setVisibility(View.GONE);
                    isOtherType=false;
                }
                expenseModel.setTypeOfExpense(expenseType.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void setSpinnerListener() {
        setExpenseTypeListener();
        setExpenseCurrencyListener();
    }

    private void getValuesFromComponents() {
        expenseComments = (EditText) findViewById(R.id.expenseComments);
        otherType = (EditText) findViewById(R.id.otherType);
        expenseAmount = (EditText) findViewById(R.id.expenseAmount);
        expenseType = (Spinner) findViewById(R.id.expenseTypeSpinner);
        expenseCurrency = (Spinner) findViewById(R.id.expenseCurrencySpinner);
        otherType = (EditText)findViewById(R.id.otherType);
        expenseModel.setCurrency("GBP");
        expenseModel.setTypeOfExpense("Food");
    }

    private void setExpenseValues(){
        String valueAmt = expenseAmount.getText().toString().trim();
        if(!valueAmt.isEmpty())
            expenseModel.setExpenseAmount(Float.parseFloat(valueAmt));
        else
            expenseModel.setExpenseAmount(0);
        expenseModel.setComments(expenseComments.getText().toString().trim());

        if(isOtherType)
            expenseModel.setOtherType(otherType.getText().toString().trim());
        else
            expenseModel.setOtherType("");
    }

    private boolean validateForm() {
        setExpenseValues();
        if(expenseModel.getExpenseAmount() > 0 && expenseModel.getComments().length()>0 && (isOtherType == true ? expenseModel.getOtherType().length()>0 : true)){
            return true;
        }
        else{
            raiseToast("Please Enter Details");
            return false;
        }
    }

    private void nextExpensePage(){
        Intent i = new Intent(AddNewExpense1.this,AddNewExpense2.class);
        i.putExtra("expenseModel",expenseModel);
        startActivity(i);
    }

    public void nextButtonClick(View view){
        boolean isValid = validateForm();
        if(isValid){
            nextExpensePage();
        }
        /*expType= expenseType.getSelectedItem().toString();
        expCurr = expenseCurrency.getSelectedItem().toString();
        expComm = expenseComments.getText().toString();
        expAmt = expenseAmount.getText().toString();
        expOtherType = otherType.getText().toString();

        if(expAmt.isEmpty()){
            raiseToast("Please enter the Amount of Expense");
        }

        if(expType.isEmpty()){
            raiseToast("Please choose the Type of Expense");
        }

        if(expCurr.isEmpty()){
            raiseToast("Please choose the Currency of Expense");
        }

        else{
            Intent addExpenseNextPage = new Intent(AddNewExpense1.this, AddNewExpense2.class);
            addExpenseNextPage.putExtra(AddNewExpense2.expComm,expComm);
            addExpenseNextPage.putExtra(AddNewExpense2.expAmt,expAmt);
            addExpenseNextPage.putExtra(AddNewExpense2.expType,expType);
            addExpenseNextPage.putExtra(AddNewExpense2.expCurr,expCurr);
            if(!expOtherType.isEmpty()){
                addExpenseNextPage.putExtra(AddNewExpense2.otherType,expOtherType);
            }
            startActivity(addExpenseNextPage);
        }*/
    }

    private void raiseToast(String toastMsg){
        Toast alertText = Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT);
        alertText.show();
    }
}