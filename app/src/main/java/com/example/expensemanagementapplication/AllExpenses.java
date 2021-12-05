package com.example.expensemanagementapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AllExpenses extends AppCompatActivity {

    public static final String tripIdString = "";
    public int tripId;
    private int totalExp=0;
    ArrayList<ExpenseCardModel> cardModel = new ArrayList<>();
    RecyclerView recycler;
    TextView totalExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_expenses);

        //tripId = Integer.parseInt(tripIdString);
        Intent i = getIntent();
        String t = i.getStringExtra(tripIdString);
        tripId = Integer.parseInt(t);

        totalExpense = findViewById(R.id.totalExp);
        createCardModelData(tripId);

        totalExpense.setText("Total: "+Integer.toString(totalExp));

        recycler = findViewById(R.id.expRecycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(manager);

        ExpenseRecyclerViewAdapter adaptor = new ExpenseRecyclerViewAdapter(cardModel, this);
        recycler.setAdapter(adaptor);
    }

    private void createCardModelData(int tripId){
        if(tripId!=0){
            DataBaseExecution db = new DataBaseExecution(this);
            Cursor expenseResults = db.getData("expense_id, amount_of_expense, type_of_expense, time_of_expense, comments","expense","trip_id",String.valueOf(tripId));
            ArrayList<ExpenseDetailModel> allExpenseDetails = getExpenseDetailsList(expenseResults);
            setCardModelData(allExpenseDetails);
        }
        else{
            raiseToast("No trips found!");
        }
    }

    private void setCardModelData(ArrayList<ExpenseDetailModel> allExpDetails) {
        for(ExpenseDetailModel exp : allExpDetails){
            String type = exp.getExpense_type();
            String time = exp.getTime_of_expense();
            String com = exp.getComments();
            String amt = Integer.toString(exp.getExpense_amount());
            cardModel.add(new ExpenseCardModel(type, time, amt, com));
            totalExp+= Integer.parseInt(amt);
        }

    }

    private ArrayList<ExpenseDetailModel> getExpenseDetailsList(Cursor expenseResults) {
        ArrayList<ExpenseDetailModel> allExpenseList = new ArrayList<>();

        expenseResults.moveToFirst();
        while(!expenseResults.isAfterLast()){
            ExpenseDetailModel exp = new ExpenseDetailModel();

            exp.setExpense_id(expenseResults.getInt(0));
            exp.setExpense_amount(expenseResults.getInt(1));
            exp.setExpense_type(expenseResults.getString(2));
            exp.setTime_of_expense(expenseResults.getString(3));
            exp.setComments(expenseResults.getString(4));

            allExpenseList.add(exp);

            expenseResults.moveToNext();
        }
        return allExpenseList;
    }

    private void raiseToast(String toastMsg){
        Toast alertText = Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT);
        alertText.show();
    }
}