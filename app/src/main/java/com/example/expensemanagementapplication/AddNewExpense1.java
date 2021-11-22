package com.example.expensemanagementapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class AddNewExpense1 extends AppCompatActivity {

    EditText expenseComments, expenseAmount;
    Spinner expenseType, expenseCurrency;
    String expComm, expAmt, expType, expCurr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_expense1);

        expenseComments = (EditText) findViewById(R.id.expenseComments);
        expenseAmount = (EditText) findViewById(R.id.expenseAmount);
        expenseType = (Spinner) findViewById(R.id.expenseTypeSpinner);
        expenseCurrency = (Spinner) findViewById(R.id.expenseCurrencySpinner);

        expenseType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(expenseType.getItemAtPosition(position).equals("Others")){
                    //pop up for edit text
                    raiseToast("pop up happened");
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

        if(expAmt.isEmpty()){
            raiseToast("Please enter the Amount of Expense");
        }

        if(expType.isEmpty()){
            raiseToast("Please choose the Type of Expense");
        }

        if(expCurr.isEmpty()){
            raiseToast("Please choose teh Currency of Expense");
        }

        else{
            Intent addExpenseNextPage = new Intent(AddNewExpense1.this, AddNewExpense2.class);
            addExpenseNextPage.putExtra(AddNewExpense2.expComm,expComm);
            addExpenseNextPage.putExtra(AddNewExpense2.expAmt,expAmt);
            addExpenseNextPage.putExtra(AddNewExpense2.expType,expType);
            addExpenseNextPage.putExtra(AddNewExpense2.expCurr,expCurr);
            startActivity(addExpenseNextPage);
        }
    }

    private void raiseToast(String toastMsg){
        Toast alertText = Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT);
        alertText.show();
    }
}