package com.example.expensemanagementapplication;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;

public class TripCardModel {
    PieData pieChart;
    int proComp;
    String tripDetails;

    public TripCardModel(PieData pieChart, int proComp, String tripDetails) {
        this.pieChart = pieChart;
        this.proComp = proComp;
        this.tripDetails = tripDetails;
    }

    public PieData getPieChart() {
        return pieChart;
    }

    public int getProgress1() { return proComp; }

    public String getTextView() { return tripDetails; }

}
