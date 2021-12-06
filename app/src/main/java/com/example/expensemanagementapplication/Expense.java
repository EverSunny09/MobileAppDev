package com.example.expensemanagementapplication;

public class Expense {
    private String name;
    private String type;
    private String amt;
    private String dateTime;

    public Expense(String name, String type, String amt, String dateTime) {
        this.name = name;
        this.type = type;
        this.amt = amt;
        this.dateTime = dateTime;
    }

    public Expense() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
