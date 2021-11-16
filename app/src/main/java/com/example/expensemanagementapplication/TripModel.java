package com.example.expensemanagementapplication;

public class TripModel {
    private int TripId;
    private int UserId;
    private String TripName;
    private String Destination;
    private String TripStartDate;
    private String TripEndDate;
    private int RequireRiskAssessment;
    private String Description;
    private int IsActive;
    private String TypeOfTrip;
    private String OtherType;
    private String TotalCompensated;
    private int IsInternationalTrip;
    private String TotalExpense;

    public TripModel(int tripId, int userId, String tripName, String destination, String tripStartDate, String tripEndDate, int requireRiskAssessment, String description, int isActive, String typeOfTrip, String otherType, String totalCompensated, int isInternationalTrip, String totalExpense) {
        TripId = tripId;
        UserId = userId;
        TripName = tripName;
        Destination = destination;
        TripStartDate = tripStartDate;
        TripEndDate = tripEndDate;
        RequireRiskAssessment = requireRiskAssessment;
        Description = description;
        IsActive = isActive;
        TypeOfTrip = typeOfTrip;
        OtherType = otherType;
        TotalCompensated = totalCompensated;
        IsInternationalTrip = isInternationalTrip;
        TotalExpense = totalExpense;
    }

    public int getTripId() {
        return TripId;
    }

    public void setTripId(int tripId) {
        TripId = tripId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getTripName() {
        return TripName;
    }

    public void setTripName(String tripName) {
        TripName = tripName;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getTripStartDate() {
        return TripStartDate;
    }

    public void setTripStartDate(String tripStartDate) {
        TripStartDate = tripStartDate;
    }

    public String getTripEndDate() {
        return TripEndDate;
    }

    public void setTripEndDate(String tripEndDate) {
        TripEndDate = tripEndDate;
    }

    public int getRequireRiskAssessment() {
        return RequireRiskAssessment;
    }

    public void setRequireRiskAssessment(int requireRiskAssessment) {
        RequireRiskAssessment = requireRiskAssessment;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getIsActive() {
        return IsActive;
    }

    public void setIsActive(int isActive) {
        IsActive = isActive;
    }

    public String getTypeOfTrip() {
        return TypeOfTrip;
    }

    public void setTypeOfTrip(String typeOfTrip) {
        TypeOfTrip = typeOfTrip;
    }

    public String getOtherType() {
        return OtherType;
    }

    public void setOtherType(String otherType) {
        OtherType = otherType;
    }

    public String getTotalCompensated() {
        return TotalCompensated;
    }

    public void setTotalCompensated(String totalCompensated) {
        TotalCompensated = totalCompensated;
    }

    public int getIsInternationalTrip() {
        return IsInternationalTrip;
    }

    public void setIsInternationalTrip(int isInternationalTrip) {
        IsInternationalTrip = isInternationalTrip;
    }

    public String getTotalExpense() {
        return TotalExpense;
    }

    public void setTotalExpense(String totalExpense) {
        TotalExpense = totalExpense;
    }

    public TripModel() {

    }

    @Override
    public String toString() {
        return "TripModel{" +
                "TripId=" + TripId +
                ", UserId=" + UserId +
                ", TripName='" + TripName + '\'' +
                ", Destination='" + Destination + '\'' +
                ", TripStartDate='" + TripStartDate + '\'' +
                ", TripEndDate='" + TripEndDate + '\'' +
                ", RequireRiskAssessment=" + RequireRiskAssessment +
                ", Description='" + Description + '\'' +
                ", IsActive=" + IsActive +
                ", TypeOfTrip='" + TypeOfTrip + '\'' +
                ", OtherType='" + OtherType + '\'' +
                ", TotalCompensated='" + TotalCompensated + '\'' +
                ", IsInternationalTrip=" + IsInternationalTrip +
                ", TotalExpense='" + TotalExpense + '\'' +
                '}';
    }
}
