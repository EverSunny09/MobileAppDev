package com.example.expensemanagementapplication;

import java.util.ArrayList;
import java.util.List;

public class TripClass {
    private String name;
    private String totalAmt;
    private String destination;
    private String startDate;
    private String endDate;
    private String riskAssess;
    private String description;
    private String type;
    List<Expense> expenseList;

    public TripClass(String name, String totalAmt, String destination, String startDate, String endDate, String riskAssess, String description, String type, List<Expense> expenseList) {
        this.name = name;
        this.totalAmt = totalAmt;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.riskAssess = riskAssess;
        this.description = description;
        this.type = type;
        this.expenseList = expenseList;
    }

    public TripClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(String totalAmt) {
        this.totalAmt = totalAmt;
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

    public String getRiskAssess() {
        return riskAssess;
    }

    public void setRiskAssess(String riskAssess) {
        this.riskAssess = riskAssess;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }
}
