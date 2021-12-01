package com.example.expensemanagementapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import javax.xml.datatype.DatatypeConfigurationException;

public class ExpenseModel implements Parcelable {

    private static int expenseId;
    private static int tripId;
    private static String typeOfExpense;
    private static String Currency;
    private static long expenseDateTime;
    private static String comments;
    private static float expenseAmount;
    private static String otherType;


    public ExpenseModel(){

    }

    public ExpenseModel(int expenseId, int tripId, String typeOfExpense, String currency, long expenseDateTime, String comments, float expenseAmount, String otherType) {
        this.expenseId = expenseId;
        this.tripId = tripId;
        this.typeOfExpense = typeOfExpense;
        this.Currency = currency;
        this.expenseDateTime = expenseDateTime;
        this.comments = comments;
        this.expenseAmount = expenseAmount;
        this.otherType = otherType;
    }

    protected ExpenseModel(Parcel in) {
        expenseId = in.readInt();
        tripId = in.readInt();
        typeOfExpense = in.readString();
        Currency = in.readString();
        expenseDateTime = in.readLong();
        comments = in.readString();
        expenseAmount = in.readInt();
        otherType = in.readString();
    }

    public static final Creator<ExpenseModel> CREATOR = new Creator<ExpenseModel>() {
        @Override
        public ExpenseModel createFromParcel(Parcel in) {
            return new ExpenseModel(in);
        }

        @Override
        public ExpenseModel[] newArray(int size) {
            return new ExpenseModel[size];
        }
    };

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

    public String getTypeOfExpense() {
        return typeOfExpense;
    }

    public void setTypeOfExpense(String typeOfExpense) {
        this.typeOfExpense = typeOfExpense;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
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

    public float getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(float expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String getOtherType() {
        return otherType;
    }

    public void setOtherType(String otherType) {
        this.otherType = otherType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
