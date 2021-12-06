package com.example.expensemanagementapplication;

import java.util.ArrayList;

public class DetailList {

    private ArrayList<TripClass> trip;

    public DetailList() {
    }
    public DetailList(ArrayList<TripClass> trip) {
        this.trip = trip;
    }

    public ArrayList<TripClass> getTrip() {
        return trip;
    }

    public void setTrip(ArrayList<TripClass> trip) {
        this.trip = trip;
    }
}
