package com.example.expensemanagementapplication;

import java.util.Date;

import javax.xml.datatype.DatatypeConfigurationException;

public class ExpenseModel {

    private static int expenseId;
    private static int tripId;
    private static int typeOfExpense;
    private static int Currency;
    private static long expenseDateTime;
    private static String comments;
    private static int expenseAmount;
    private static String otherType;

    public ExpenseModel(int expenseId, int tripId, int typeOfExpense, int currency, long expenseDateTime, String comments, int expenseAmount, String otherType) {
        this.expenseId = expenseId;
        this.tripId = tripId;
        this.typeOfExpense = typeOfExpense;
        this.Currency = currency;
        this.expenseDateTime = expenseDateTime;
        this.comments = comments;
        this.expenseAmount = expenseAmount;
        this.otherType = otherType;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getTypeOfExpense() {
        return typeOfExpense;
    }

    public void setTypeOfExpense(int typeOfExpense) {
        this.typeOfExpense = typeOfExpense;
    }

    public int getCurrency() {
        return Currency;
    }

    public void setCurrency(int currency) {
        this.Currency = currency;
    }

    public long getExpenseDateTime() {
        return expenseDateTime;
    }

    public void setExpenseDateTime(long expenseDateTime) {
        this.expenseDateTime = expenseDateTime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(int expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String getOtherType() {
        return otherType;
    }

    public void setOtherType(String otherType) {
        this.otherType = otherType;
    }
}
