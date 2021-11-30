package com.example.expensemanagementapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExpenseDetailModel {
    int expense_id;
    String expense_type;
    int expense_amount;
    int trip_id;

    public ExpenseDetailModel(int expense_id, String expense_type, int expense_amount, int trip_id) {
        this.expense_id = expense_id;
        this.expense_type = expense_type;
        this.expense_amount = expense_amount;
        this.trip_id = trip_id;
    }

    public ExpenseDetailModel() {
    }

    public int getExpense_id() {
        return expense_id;
    }

    public void setExpense_id(int expense_id) {
        this.expense_id = expense_id;
    }

    public String getExpense_type() {
        return expense_type;
    }

    public void setExpense_type(String expense_type) {
        this.expense_type = expense_type;
    }

    public int getExpense_amount() {
        return expense_amount;
    }

    public void setExpense_amount(int expense_amount) {
        this.expense_amount = expense_amount;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

}
