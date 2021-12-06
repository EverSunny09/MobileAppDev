package com.example.expensemanagementapplication;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;

public class TripCardModel {
    PieData pieChart;
    String tripName, tripDesc, tripDate, tripDest;
    Boolean isInt, isRisk;


    public TripCardModel(PieData pieChart, String tripName, String tripDesc, String tripDate, String tripDest, Boolean isInt, Boolean isRisk) {
        this.pieChart = pieChart;
        this.tripName = tripName;
        this.tripDesc = tripDesc;
        this.tripDate = tripDate;
        this.tripDest = tripDest;
        this.isInt = isInt;
        this.isRisk = isRisk;
    }

    public PieData getPieChart() {
        return pieChart;
    }

    public void setPieChart(PieData pieChart) {
        this.pieChart = pieChart;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getTripDesc() {
        return tripDesc;
    }

    public void setTripDesc(String tripDesc) {
        this.tripDesc = tripDesc;
    }

    public String getTripDest() {
        return tripDest;
    }

    public void setTripDest(String tripDest) {
        this.tripDest = tripDest;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public Boolean getIsInt() {
        return isInt;
    }

    public void setIsInt(Boolean isInt) {
        this.isInt = isInt;
    }

    public Boolean getIsRisk() {
        return isRisk;
    }

    public void setIsRisk(Boolean isRisk) {
        this.isRisk = isRisk;
    }
}
