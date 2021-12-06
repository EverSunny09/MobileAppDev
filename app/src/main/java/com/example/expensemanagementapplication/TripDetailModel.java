package com.example.expensemanagementapplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TripDetailModel {
    int id;
    String tripName;
    String destination;
    String description;
    String startDate;
    String endDate;
    int totalExpense;
    int totalCompesation;
    List<ExpenseDetailModel> allExpenses;
    int isInt, isRisk;

    public TripDetailModel(int id, String tripName, String destination, String startDate, String endDate, int totalExpense, int totalCompesation,String description, int isInt, int isRisk) {
        this.id = id;
        this.tripName = tripName;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalExpense = totalExpense;
        this.totalCompesation = totalCompesation;
        this.description=description;
        this.isInt=isInt;
        this.isRisk=isRisk;
    }

    public List<ExpenseDetailModel> getAllExpenses() {
        return allExpenses;
    }

    public void setAllExpenses(List<ExpenseDetailModel> allExpenses) {
        this.allExpenses = allExpenses;
    }

    public TripDetailModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(int totalExpense) {
        this.totalExpense = totalExpense;
    }

    public int getTotalCompesation() {
        return totalCompesation;
    }

    public void setTotalCompesation(int totalCompesation) {
        this.totalCompesation = totalCompesation;
    }

    public int getInt() {
        return isInt;
    }

    public void setInt(int anInt) {
        isInt = anInt;
    }

    public int getRisk() {
        return isRisk;
    }

    public void setRisk(int risk) {
        isRisk = risk;
    }
}
