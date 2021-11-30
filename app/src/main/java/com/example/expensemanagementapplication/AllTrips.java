package com.example.expensemanagementapplication;

import androidx.annotation.IntegerRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AllTrips extends AppCompatActivity {

    int userId;
    public static final String userName= "abc";
    private PieChart pieChart;
    ArrayList<TripCardModel> cardModel = new ArrayList<>();
    RecyclerView recycler1;
    PieData pieData1, pieData2, pieData3;
    ProgressBar p1,p2,p3;
    TextView t1;
    ArrayList<Integer> allTrips = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trips);

        //pieChart = findViewById(R.id.pieChart);
        recycler1 = findViewById(R.id.recycler1);

        userId = getUserId();
        allTrips = getUserAllTrips(userId);
        createCardModelData(allTrips);

        //cardModel.add(new TripCardModel(pieData1 = loadPieChartData(),p1,p2,t1));
       // cardModel.add(new TripCardModel(pieData2 = loadPieChartData()));
       // cardModel.add(new TripCardModel(pieData3 = loadPieChartData()));

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

    private PieData loadPieChartData(List<ExpenseDetailModel> allExp){

        ArrayList<PieEntry> pieChartEntries = new ArrayList<>();

        for(ExpenseDetailModel exp: allExp){
            pieChartEntries.add(new PieEntry(exp.getExpense_amount(),exp.getExpense_type()));
        }

        ArrayList<Integer>colors = new ArrayList<>();
        for (int color : ColorTemplate.MATERIAL_COLORS){
            colors.add(color);
        }
        for (int color : ColorTemplate.PASTEL_COLORS){
            colors.add(color);
        }

        PieDataSet pieDataSet = new PieDataSet(pieChartEntries,"Trip Monthly Expense");
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

    private ArrayList<Integer> getUserAllTrips(int userId){
        DataBaseExecution db = new DataBaseExecution(this);
        ArrayList<Integer> trips = new ArrayList<Integer>();
        Cursor allTripsData = db.getData("trip_id","trip","user_id",String.valueOf(userId));
        allTripsData.moveToFirst();
        while(!allTripsData.isAfterLast()){
            trips.add(allTripsData.getInt(0));
            allTripsData.moveToNext();
        }
        return trips;
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void createCardModelData(ArrayList<Integer> trips){
        if(!trips.isEmpty()){
            DataBaseExecution db = new DataBaseExecution(this);
            Cursor tripResults = db.getAllTripsDetails(trips);
            Cursor expenseResults = db.getAllExpenseDetails(trips);
            ArrayList<ExpenseDetailModel> allExpenseDetails = getExpenseDetailsList(expenseResults);
            ArrayList<TripDetailModel> allTripDetails = getTripDetailsList(tripResults, allExpenseDetails);
            setCardModelData(allTripDetails);
/*
            for(int eachTrip : trips){
                Cursor expIdDb = db.getData("expense_id,expense_type,amount_of_expense","expense","trip_id",eachTrip);
                expIdDb.moveToFirst();
                String expId = expIdDb.getString(0);
                String expType = expIdDb.getString(1);
                int expAmt = Integer.parseInt(expIdDb.getString(2));

                Cursor tripInfoDb = db.getData("trip_name,destination,trip_start_date,trip_end_date,total_expense,total_compensated","trip","trip_id",eachTrip);
                tripInfoDb.moveToFirst();
                String tripName = tripInfoDb.getString(0);
                String tripDest = tripInfoDb.getString(1);
                String startDate = tripInfoDb.getString(2);
                String endDate = tripInfoDb.getString(3);
                int totalExp = Integer.parseInt(tripInfoDb.getString(4));
                int totalComp = Integer.parseInt(tripInfoDb.getString(5));
                int percent = totalExp/totalComp;

                float expAmount = totalExp/expAmt ;
                PieData tripChart = loadPieChartData(expType,expAmount);

                ProgressBar proComp = findViewById(R.id.totalCompBar);
                proComp.setProgress(percent);

                TextView tripDetails = findViewById(R.id.tripDetailView);
                String tripHeader = "<b>"+tripName+"<b>\r\n"+tripDest+"\r\n<i>"+startDate+" - "+endDate;
                tripDetails.setText(tripHeader);

                tripModels.add(new TripCardModel(tripChart,proComp,tripDetails));
            }*/
        }
        else{
            raiseToast("No trips found!");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private ArrayList<ExpenseDetailModel> getExpenseDetailsList(Cursor expenseResults) {
        ArrayList<ExpenseDetailModel> allExpenseList = new ArrayList<>();

        expenseResults.moveToFirst();
        while(!expenseResults.isAfterLast()){
            ExpenseDetailModel exp = new ExpenseDetailModel();

            exp.setTrip_id(expenseResults.getInt(0));
            exp.setExpense_id(expenseResults.getInt(1));
            exp.setExpense_amount(expenseResults.getInt(2));
            exp.setExpense_type(expenseResults.getString(3));

            allExpenseList.add(exp);

            expenseResults.moveToNext();
        }
        return allExpenseList;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setCardModelData(ArrayList<TripDetailModel> allTripDetails) {

        //ArrayList<TripCardModel> tripModels = new ArrayList<>();

        for(TripDetailModel trip : allTripDetails){

            PieData tripChart = loadPieChartData(trip.getAllExpenses());

            //float expAmount = trip.getTotalExpense()/expAmt ;
            //PieData tripChart = loadPieChartData(expType,expAmount);

            String tripDetails = "<b>" + trip.getTripName() + "</b> /r/n<i>" + trip.getStartDate() + "-" + trip.getEndDate() + "</i>";

            cardModel.add(new TripCardModel(tripChart,trip.getTotalCompesation(),tripDetails));
        }

    }

    private void raiseToast(String toastMsg){
        Toast alertText = Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT);
        alertText.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<TripDetailModel> getTripDetailsList(Cursor result, ArrayList<ExpenseDetailModel> allExpenses) {
        ArrayList<TripDetailModel> allTripsList = new ArrayList<>();

        result.moveToFirst();
        while(!result.isAfterLast()){
            TripDetailModel trip = new TripDetailModel();

            trip.setId(result.getInt(0));
            trip.setTripName(result.getString(1));
            trip.setDestination(result.getString(2));
            trip.setStartDate(result.getString(3));
            trip.setEndDate(result.getString(4));
            trip.setTotalExpense(result.getInt(5));
            trip.setTotalCompesation(result.getInt(6));
            trip.setAllExpenses(setTripExpense(result.getInt(0), allExpenses));

            allTripsList.add(trip);

            result.moveToNext();
        }
        return allTripsList;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<ExpenseDetailModel> setTripExpense(int tripId, List<ExpenseDetailModel> allExpenseDetails) {
        return allExpenseDetails.stream().filter(s->s.getTrip_id() == tripId).collect(Collectors.toList());
        /*for(ExpenseDetailModel exp : allExpenseDetails){
            if(tripId==exp.getTrip_id()){
                expenses.add(exp);
            }
        }
        return expenses;*/
    }

    public int getUserId(){
        SessionManagement sessionManagement=new SessionManagement(AllTrips.this);
        return sessionManagement.getSession();
    }
}
