package com.example.expensemanagementapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class TripModel implements Parcelable {
    private int TripId;
    private int UserId;
    private String TripName;
    private String Destination;
    private long TripStartDate;
    private long TripEndDate;
    private int RequireRiskAssessment;
    private String Description;
    private int IsActive;
    private String TypeOfTrip;
    private String OtherType;
    private String TotalCompensated;
    private int IsInternationalTrip;
    private String TotalExpense;
    private int TypeOfTripId;


    @Override
    public String toString() {
        return "TripModel{" +
                "TripId=" + TripId +
                ", UserId=" + UserId +
                ", TripName='" + TripName + '\'' +
                ", Destination='" + Destination + '\'' +
                ", TripStartDate=" + TripStartDate +
                ", TripEndDate=" + TripEndDate +
                ", RequireRiskAssessment=" + RequireRiskAssessment +
                ", Description='" + Description + '\'' +
                ", IsActive=" + IsActive +
                ", TypeOfTrip='" + TypeOfTrip + '\'' +
                ", OtherType='" + OtherType + '\'' +
                ", TotalCompensated='" + TotalCompensated + '\'' +
                ", IsInternationalTrip=" + IsInternationalTrip +
                ", TotalExpense='" + TotalExpense + '\'' +
                ", TypeOfTripId=" + TypeOfTripId +
                '}';
    }



    public TripModel(int tripId, int userId, String tripName, String destination, long tripStartDate, long tripEndDate, int requireRiskAssessment, String description, int isActive, String typeOfTrip, String otherType, String totalCompensated, int isInternationalTrip, String totalExpense,int typeOfTripId) {
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
        TypeOfTripId=typeOfTripId;
    }

    protected TripModel(Parcel in) {
        TripId = in.readInt();
        UserId = in.readInt();
        TripName = in.readString();
        Destination = in.readString();
        TripStartDate = in.readLong();
        TripEndDate = in.readLong();
        RequireRiskAssessment = in.readInt();
        Description = in.readString();
        IsActive = in.readInt();
        TypeOfTrip = in.readString();
        OtherType = in.readString();
        TotalCompensated = in.readString();
        IsInternationalTrip = in.readInt();
        TotalExpense = in.readString();
        TypeOfTripId = in.readInt();
    }

    public static final Creator<TripModel> CREATOR = new Creator<TripModel>() {
        @Override
        public TripModel createFromParcel(Parcel in) {
            return new TripModel(in);
        }

        @Override
        public TripModel[] newArray(int size) {
            return new TripModel[size];
        }
    };

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

    public long getTripStartDate() {
        return TripStartDate;
    }

    public void setTripStartDate(long tripStartDate) {
        TripStartDate = tripStartDate;
    }

    public long getTripEndDate() {
        return TripEndDate;
    }

    public void setTripEndDate(long tripEndDate) {
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

    public int getTypeOfTripId() {
        return TypeOfTripId;
    }

    public void setTypeOfTripId(int typeOfTripId) {
        TypeOfTripId = typeOfTripId;
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(TripId);
        dest.writeInt(UserId);
        dest.writeString(TripName);
        dest.writeString(Destination);
        dest.writeLong(TripStartDate);
        dest.writeLong(TripEndDate);
        dest.writeInt(RequireRiskAssessment);
        dest.writeString(Description);
        dest.writeInt(IsActive);
        dest.writeString(TypeOfTrip);
        dest.writeString(OtherType);
        dest.writeString(TotalCompensated);
        dest.writeInt(IsInternationalTrip);
        dest.writeString(TotalExpense);
        dest.writeInt(TypeOfTripId);
    }
}
