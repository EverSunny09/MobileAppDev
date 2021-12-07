package com.example.expensemanagementapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AllTrips extends AppCompatActivity {

    int userId;
    public static final String userName= "abc";
    private PieChart pieChart;
    ArrayList<TripCardModel> cardModel = new ArrayList<>();
    RecyclerView recycler1;
    ArrayList<Integer> allTrips = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trips);

        recycler1 = findViewById(R.id.recycler1);

        userId = getUserId();
        allTrips = getUserAllTrips(userId);
        createCardModelData(allTrips);

        LinearLayoutManager manager1 = new LinearLayoutManager(this);
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler1.setLayoutManager(manager1);

        RecyclerViewAdapter adaptor1 = new RecyclerViewAdapter(this,cardModel);
        recycler1.setAdapter(adaptor1);
    }

    private PieData loadPieChartData(List<ExpenseDetailModel> allExp){

        ArrayList<PieEntry> pieChartEntries = new ArrayList<>();

        for(ExpenseDetailModel exp: allExp){
            pieChartEntries.add(new PieEntry(exp.getExpense_amount(),exp.getExpense_type()));
        }

        ArrayList<Integer>colors = new ArrayList<>();
        /*for (int color : ColorTemplate.PASTEL_COLORS){
            colors.add(color);
        }*/
        for (int color : ColorTemplate.JOYFUL_COLORS){
            colors.add(color);
        }

        PieDataSet pieDataSet = new PieDataSet(pieChartEntries,"Trip Expense");
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
            Boolean isInternational=false;
            Boolean isRiskAssessment=false;

            PieData tripChart = loadPieChartData(trip.getAllExpenses());
            String tripName = trip.getTripName();
            String tripDesc = trip.getDescription();
            String tripDest = trip.getDestination();
            String tripDate = getDateTimeFormat(Long.parseLong(trip.getStartDate())) +" - "+ getDateTimeFormat(Long.parseLong(trip.getEndDate()));;
            int isInt = trip.getInt();
            int isRisk = trip.getRisk();

            if(isInt==1){
                isInternational=true;
            }
            if (isRisk==1){
                isRiskAssessment=true;
            }

            cardModel.add(new TripCardModel(tripChart,tripName,tripDesc,tripDate,tripDest,isInternational,isRiskAssessment));
           // cardModel.add(new TripCardModel(tripChart,trip.getTotalCompesation(),tripDetails));
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
            trip.setDescription(result.getString(7));
            trip.setInt(result.getInt(8));
            trip.setRisk(result.getInt(9));

            trip.setAllExpenses(setTripExpense(result.getInt(0), allExpenses));

            allTripsList.add(trip);

            result.moveToNext();
        }
        return allTripsList;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<ExpenseDetailModel> setTripExpense(int tripId, List<ExpenseDetailModel> allExpenseDetails) {
        return allExpenseDetails.stream().filter(s->s.getTrip_id() == tripId).collect(Collectors.toList());
    }

    public int getUserId(){
        SessionManagement sessionManagement=new SessionManagement(AllTrips.this);
        return sessionManagement.getSession();
    }

    private String getDateTimeFormat(Long DateTime){
        Date date = new Date(DateTime);
        Format format = new SimpleDateFormat("dd MMM yyyy");
        return format.format(date);
    }
}
