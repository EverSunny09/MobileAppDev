package com.example.expensemanagementapplication;

import java.util.ArrayList;

public class TripJSONModel {
    private String userId;
    ArrayList<DetailList> detailList;

    public TripJSONModel(String userId, ArrayList<DetailList> detailList) {
        this.userId = userId;
        this.detailList = detailList;
    }

    public TripJSONModel() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<DetailList> getDetailList() {
        return detailList;
    }

    public void setDetailList(ArrayList<DetailList> detailList) {
        this.detailList = detailList;
    }
}
