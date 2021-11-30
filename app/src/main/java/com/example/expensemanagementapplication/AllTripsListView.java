package com.example.expensemanagementapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
//import android.widget.SearchView;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;

public class AllTripsListView extends AppCompatActivity {

    ListView listView;
    ListViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trips_list_view);

        listView = findViewById(R.id.allTripsListView);
        ArrayList<TripDetailModel> arrayList = new ArrayList<>();
        arrayList.addAll(getAllTrips());
        adapter = new ListViewAdapter(this, arrayList);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    adapter.filter("");
                    listView.clearTextFilter();
                } else {
                    adapter.filter(newText);
                }
                return true;
            }
        });

        return true;
    }

    public int getUserId(){
        SessionManagement sessionManagement=new SessionManagement(AllTripsListView.this);
        return sessionManagement.getSession();
    }
    public ArrayList<TripDetailModel> getAllTrips() {
        DataBaseExecution db = new DataBaseExecution(this);
        Cursor allTripsData = db.getData("*","trip","user_id",String.valueOf(getUserId()));
        return getTripDetails(allTripsData);
    }
    public ArrayList<TripDetailModel> getTripDetails(Cursor allTripsData){
        ArrayList<TripDetailModel> allTripList = new ArrayList<>();

        allTripsData.moveToFirst();
        while(!allTripsData.isAfterLast()){
            TripDetailModel exp = new TripDetailModel();

            exp.setId(allTripsData.getInt(0));
            exp.setTripName(allTripsData.getString(2));
            exp.setDestination(allTripsData.getString(3));
            exp.setStartDate(allTripsData.getString(4));
            exp.setEndDate(allTripsData.getString(5));
            exp.setDescription(allTripsData.getString(7));
            allTripList.add(exp);

            allTripsData.moveToNext();
        }
        return allTripList;
    }
}