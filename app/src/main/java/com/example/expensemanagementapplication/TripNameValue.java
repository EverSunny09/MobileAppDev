package com.example.expensemanagementapplication;

public class TripNameValue {
    private String id;
    private String tripName;

    public TripNameValue(String id, String tripName) {
        this.id = id;
        this.tripName = tripName;
    }

    public TripNameValue(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    //to display object as a string in spinner
    @Override
    public String toString() {
        return tripName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TripNameValue){
            TripNameValue t = (TripNameValue )obj;
            if(t.getTripName().equals(tripName) && t.getId()==id ) return true;
        }

        return false;
    }

}
