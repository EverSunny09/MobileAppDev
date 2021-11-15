package com.example.expensemanagementapplication;

import android.widget.ProgressBar;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;

public class TripCardModel {
    PieData piechart;
    ProgressBar progress1;
    ProgressBar progress2;
    ProgressBar progress3;

    public TripCardModel(PieData piechart, ProgressBar progress1, ProgressBar progress2, ProgressBar progress3) {
        this.piechart = piechart;
        this.progress1 = progress1;
        this.progress2 = progress2;
        this.progress3 = progress3;
    }

    public PieData getPiechart() {
        return piechart;
    }


    public ProgressBar getProgress1() {
        return progress1;
    }

    public ProgressBar getProgress2() {
        return progress2;
    }


    public ProgressBar getProgress3() {
        return progress3;
    }

}
