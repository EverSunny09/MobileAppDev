package com.example.expensemanagementapplication;

public class Expense {
    private int trip_id;
    private String type;
    private String amt;
    private String dateTime;

    public Expense(String type, String amt, String dateTime,int trip_id) {
        this.type = type;
        this.amt = amt;
        this.dateTime = dateTime;
        this.trip_id=trip_id;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public Expense() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
