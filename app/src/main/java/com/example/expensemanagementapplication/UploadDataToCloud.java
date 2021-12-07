package com.example.expensemanagementapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class UploadDataToCloud extends AppCompatActivity {
    public WebView browser;
    private String url="https://stuiis.cms.gre.ac.uk/COMP1424CoreWS/comp1424cwstring";
    private String Json;
    TextView jsonRequest,jsonResponse;
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_data_to_cloud);
        browser = findViewById(R.id.webkit);
        jsonRequest = findViewById(R.id.jsonRequest);
        jsonResponse = findViewById(R.id.jsonResponse);
        Json = createTripJSONModel();
        setBrowserJob(Json);
    }

    private void setBrowserJob(String JSON){
        try {
            jsonRequest.setText(JSON);
            URL pageURL = new URL(url);
            trustAllHosts();
            HttpURLConnection connection = (HttpURLConnection)pageURL.openConnection();

            SendJsonThread myTask = new SendJsonThread(this, connection, JSON);
            Thread thread = new Thread(myTask, "JSON Data");
            thread.start();

        } catch (IOException e) {
            jsonResponse.setText(e.toString());
            e.printStackTrace();
            Log.i("my msg","this is test");
        }
    }

    private void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }

            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
        } };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.R)
    public String createTripJSONModel(){
        TripJSONModel tripJSONModel = new TripJSONModel();
        tripJSONModel.setUserId(String.valueOf(getUserId()));
        DetailList detailList = new DetailList();
        ArrayList<Integer> tripIds = getUserAllTrips(getUserId());
        detailList.setTrip(getAllTripData(tripIds));
        tripJSONModel.setDetailList(detailList);
        return getJSON(tripJSONModel);
    }

    public String getJSON(TripJSONModel tripJSONModel){
        Gson gson = new Gson();
        return gson.toJson(tripJSONModel);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private int getUserId(){
        SessionManagement sessionManagement=new SessionManagement(UploadDataToCloud.this);
        return sessionManagement.getSession();
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
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
    private ArrayList<TripClass> getAllTripData(ArrayList<Integer> tripIds){
        if(!tripIds.isEmpty()){
            DataBaseExecution db = new DataBaseExecution(this);
            Cursor tripResults = db.getAllTripsDetails(tripIds);
            Cursor expenseResults = db.getExpenseDetails(tripIds);
            ArrayList<Expense> allExpenseDetails = getExpenseDetailsList(expenseResults);
            return getTripDetailsList(tripResults, allExpenseDetails);
        }
        else{
            return new ArrayList<TripClass>();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<TripClass> getTripDetailsList(Cursor result, ArrayList<Expense> allExpenses) {
        ArrayList<TripClass> allTripsList = new ArrayList<>();

        result.moveToFirst();
        while(!result.isAfterLast()){
            TripClass trip = new TripClass();
            trip.setName(result.getString(1));
            trip.setDestination(result.getString(2));
            trip.setStartDate(getDateFormat(Long.parseLong(result.getString(3))));
            trip.setEndDate(getDateFormat(Long.parseLong(result.getString(4))));
            trip.setDescription(result.getString(7));
            trip.setRiskAssess(result.getString(9));
            trip.setType(result.getString(10));
            trip.setExpenseList(setTripExpense(result.getInt(0), allExpenses));

            allTripsList.add(trip);

            result.moveToNext();
        }
        return allTripsList;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<Expense> setTripExpense(int tripId, List<Expense> allExpenseDetails) {
        return allExpenseDetails.stream().filter(s->s.getTrip_id() == tripId).collect(Collectors.toList());
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private ArrayList<Expense> getExpenseDetailsList(Cursor expenseResults) {
        ArrayList<Expense> allExpenseList = new ArrayList<>();

        expenseResults.moveToFirst();
        while(!expenseResults.isAfterLast()){
            Expense exp = new Expense();
            exp.setTrip_id(expenseResults.getInt(1));
            exp.setAmt(expenseResults.getString(4));
            exp.setType(expenseResults.getString(2));
            exp.setDateTime(getDateTimeFormat(Long.parseLong(expenseResults.getString(3))));

            allExpenseList.add(exp);

            expenseResults.moveToNext();
        }
        return allExpenseList;
    }
    private String getDateFormat(Long DateTime){
        Date date = new Date(DateTime);
        Format format = new SimpleDateFormat("dd MMM yyyy");
        return format.format(date);
    }

    private String getDateTimeFormat(Long DateTime){
        Date date = new Date(DateTime);
        Format format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        return format.format(date);
    }
}