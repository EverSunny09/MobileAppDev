package com.example.expensemanagementapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.CalendarView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;

public class AllTrips extends AppCompatActivity {

    private PieChart pieChart;
    ArrayList<TripCardModel> cardModel;
    RecyclerView recycler1;
    PieData pieData1, pieData2, pieData3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trips);

        //pieChart = findViewById(R.id.pieChart);
        recycler1 = findViewById(R.id.recycler1);
        cardModel = new ArrayList<>();

        cardModel.add(new TripCardModel(pieData1 = loadPieChartData()));
        cardModel.add(new TripCardModel(pieData2 = loadPieChartData()));
        cardModel.add(new TripCardModel(pieData3 = loadPieChartData()));

        LinearLayoutManager manager1 = new LinearLayoutManager(this);
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler1.setLayoutManager(manager1);

        RecyclerViewAdapter adaptor1 = new RecyclerViewAdapter(this,cardModel);
        recycler1.setAdapter(adaptor1);

        //setUpPieChart();
        //loadPieChartData();

        //review = findViewById(R.id.)

    }


    private void setUpPieChart(){
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelTextSize(Color.BLACK);
        pieChart.setCenterText("Expense for Jan Month");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }

    private PieData loadPieChartData(){

        ArrayList<PieEntry> pieChartEntries = new ArrayList<>();
        pieChartEntries.add(new PieEntry(0.2f,"Food"));
        pieChartEntries.add(new PieEntry(0.5f,"Travel"));
        pieChartEntries.add(new PieEntry(0.3f,"Accommodation"));

        ArrayList<Integer>colors = new ArrayList<>();
        for (int color : ColorTemplate.MATERIAL_COLORS){
            colors.add(color);
        }
        for (int color : ColorTemplate.PASTEL_COLORS){
            colors.add(color);
        }

        PieDataSet pieDataSet = new PieDataSet(pieChartEntries,"Monthly Expense");
        pieDataSet.setColors(colors);

        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter(pieChart));
        pieData.setValueTextSize(12f);
        pieData.setValueTextColor(Color.BLACK);

        /*pieChart.setData(pieData);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);*/
        return pieData;
    }
}