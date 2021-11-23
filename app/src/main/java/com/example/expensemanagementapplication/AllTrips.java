package com.example.expensemanagementapplication;

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
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AllTrips extends AppCompatActivity {

    int userId = 0;
    private PieChart pieChart;
    ArrayList<TripCardModel> cardModel;
    RecyclerView recycler1;
    PieData pieData1, pieData2, pieData3;
    ProgressBar p1,p2,p3;
    TextView t1;
    ArrayList<Integer> allTrips = new ArrayList<>();
    DataBaseExecution db = new DataBaseExecution(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trips);

        //pieChart = findViewById(R.id.pieChart);
        recycler1 = findViewById(R.id.recycler1);
        cardModel = new ArrayList<>();

       // allTrips = getUserAllTrips(userId);
        //createCardModelData(allTrips);

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

    private PieData loadPieChartData(String expType,float expAmount){

        ArrayList<PieEntry> pieChartEntries = new ArrayList<>();
        pieChartEntries.add(new PieEntry(expAmount,expType));
        pieChartEntries.add(new PieEntry(0.5f,"Travel"));
        pieChartEntries.add(new PieEntry(0.3f,"Accommodation"));

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
/*
    private ArrayList<Integer> getUserAllTrips(int userId){
        ArrayList<Integer> trips = new ArrayList<Integer>();
        Cursor allTripsData = db.getData("trip_id","trip","user_id",String.valueOf(userId));
        allTripsData.moveToFirst();
        while(!allTripsData.isAfterLast()){
            trips.add(allTripsData.getInt(0));
            allTripsData.moveToNext();
        }
        return trips;
    }

    private void createCardModelData(ArrayList<Integer> trips){
        if(!trips.isEmpty()){

            Cursor tripResults = db.getAllTripsDetails(trips);
            Cursor expenseResults = db.getAllExpenseDetails(trips);
            ArrayList<ExpenseDetailModel> allExpenseDetails = getExpenseDetailsList(expenseResults);
            ArrayList<TripDetailModel> allTripDetails = getTripDetailsList(tripResults, allExpenseDetails);
            setCardModelData(allTripDetails);

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
            }
        }
        else{
            raiseToast("No trips found!");
        }
    }

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
        ArrayList<TripCardModel> tripModels = new ArrayList<>();

        for(TripDetailModel trip : allTripDetails){
            //List<Long> expAmts = trip.allExpenses.stream().map(ExpenseDetailModel::getExpense_amount).collect(Collectors.toList());
            //List<ExpenseDetailModel> tripExpense = trip.allExpenses.stream().filter(s->s.getTrip_id() == trip.getId()).collect(Collectors.toList());
            List<Integer> expAmts =
            PieData tripChart = loadPieChartData(trip.allExpenses.map,expAmount);

            float expAmount = trip.getTotalExpense()/expAmt ;
            PieData tripChart = loadPieChartData(expType,expAmount);

            //setprogre
            tripModels.add(new TripCardModel(tripChart,proComp,tripDetails));
        }

    }

    private void raiseToast(String toastMsg){
        Toast alertText = Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT);
        alertText.show();
    }

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

    private ArrayList<ExpenseDetailModel> setTripExpense(int tripId, ArrayList<ExpenseDetailModel> allExpenseDetails) {
        ArrayList<ExpenseDetailModel> expenses = new ArrayList<ExpenseDetailModel>();
        for(ExpenseDetailModel exp : allExpenseDetails){
            if(tripId==exp.getTrip_id()){
                expenses.add(exp);
            }
        }
        return expenses;
    }

    public void setTripDetailsList(ArrayList<TripDetailModel> tripDetailsList) {
        this.tripDetailsList = tripDetailsList;
    }*/
}