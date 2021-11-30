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
    String expComm, expAmt, expType, expCurr, expOtherType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_expense1);

        expenseComments = (EditText) findViewById(R.id.expenseComments);
        otherType = (EditText) findViewById(R.id.otherType);
        expenseAmount = (EditText) findViewById(R.id.expenseAmount);
        expenseType = (Spinner) findViewById(R.id.expenseTypeSpinner);
        expenseCurrency = (Spinner) findViewById(R.id.expenseCurrencySpinner);

        expenseType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(expenseType.getItemAtPosition(position).equals("Others")){
                    //pop up for edit text
                    raiseToast("pop up happened");
                    otherType.setVisibility(View.VISIBLE);

                    /*AlertDialog.Builder popUP = new AlertDialog.Builder(AddNewExpense1.this);
                    popUP.setView(R.layout.other_type_dailog_box);
                    AlertDialog alertPop = popUP.create();
                    popUP.show();*/

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void nextExpensePage(View view){
        expType= expenseType.getSelectedItem().toString();
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
        }
    }

    private void raiseToast(String toastMsg){
        Toast alertText = Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT);
        alertText.show();
    }
}