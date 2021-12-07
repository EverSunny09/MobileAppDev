package com.example.expensemanagementapplication;

import java.util.ArrayList;
import java.util.List;

public class DetailList {

    private List<TripClass> trip;

    public DetailList() {
    }
    public DetailList(List<TripClass> trip) {
        this.trip = trip;
    }

    public List<TripClass> getTrip() {
        return trip;
    }

    public void setTrip(List<TripClass> trip) {
        this.trip = trip;
    }
}
