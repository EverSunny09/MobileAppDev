package com.example.expensemanagementapplication;

public enum TripTypes {
    Meeting("Meeting",1),
    Workshop("Workshop",2),
    Seminar("Seminar",3),
    Others("Others",4);

    private String tripType;

    TripTypes(String aState, int i) {
        tripType = aState;
    }

    @Override public String toString() {
        return tripType;
    }
}
