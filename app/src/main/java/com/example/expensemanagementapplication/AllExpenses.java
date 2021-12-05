package com.example.expensemanagementapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class AllExpenses extends AppCompatActivity {

    public static final String tripIdString = "m";
    private int tripId;
    ArrayList<ExpenseCardModel> cardModel = new ArrayList<>();
    ArrayList<Integer> allExpenseId = new ArrayList<>();
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_expenses);

        tripId = Integer.parseInt(tripIdString);
        createCardModelData(tripId);

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
            cardModel.add(new ExpenseCardModel(type));
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