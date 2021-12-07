package com.example.expensemanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URLEncoder;

public class SendJsonThread implements Runnable{

    private AppCompatActivity activity;
    private HttpURLConnection connection;
    private String json;

    public SendJsonThread(AppCompatActivity activity, HttpURLConnection connection, String json) {
        this.activity = activity;
        this.connection = connection;
        this.json = json;
    }

    @Override
    public void run() {
        String response = "";
        if (prepareConnection()) {
            response = postJson();
        } else {
            response = "Error preparing the connection";
        }
        showResult(response);
    }

    private void showResult(String response) {
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                String page = generatePage(response);
                ((UploadDataToCloud)activity).browser.loadData(page, "text/html", "UTF-8");
            }
        });
    }

    private String postJson() {
        String response = "";
        try {
            String postParameters = "jsonpayload=" + URLEncoder.encode(json, "UTF-8");
            connection.setFixedLengthStreamingMode(postParameters.getBytes().length);
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            out.print(postParameters);
            out.close();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                response = readStream(connection.getInputStream());
            } else {
                response = "Error contacting server: " + responseCode;
            }
        } catch (Exception e) {
            response = e.toString();//"Error executing code";
        }
        return response;
    }

    private String readStream(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private String generatePage(String content) {
        return "<html><body><p>" + content + "</p></body></html>";
    }


    private boolean prepareConnection() {
        try {
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            return true;

        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        return false;
    }
}
