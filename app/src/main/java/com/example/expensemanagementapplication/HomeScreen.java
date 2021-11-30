package com.example.expensemanagementapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {

    public static final String user = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        DataBaseExecution db = new DataBaseExecution(this);

        //db.getAllTripsDetails();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void logOut(View view){
        SessionManagement sessionManagement=new SessionManagement(HomeScreen.this);
        sessionManagement.removeSession();
        moveToLogin();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void moveToLogin() {
        Intent logIn = new Intent(HomeScreen.this, MainActivity.class);
        logIn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(logIn);
    }

    public void moveToAllTrips(View view){
        Intent allTrips = new Intent(this, AllTripsListView.class);
        startActivity(allTrips);
    }

    public void addNewTrip(View view){
        Intent addNewTrip = new Intent(HomeScreen.this, AddNewTrip1.class);
        addNewTrip.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(addNewTrip);
    }

    public void addNewExpense(View view){
        Intent addNewExpense = new Intent(HomeScreen.this, AddNewExpense1.class);
        startActivity(addNewExpense);
    }
    private void raiseToast(String toastMsg){
        Toast alertText = Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT);
        alertText.show();
    }
}