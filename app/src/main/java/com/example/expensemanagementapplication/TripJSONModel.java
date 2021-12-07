package com.example.expensemanagementapplication;

import java.util.ArrayList;

public class TripJSONModel {
    private String userId;
    DetailList detailList;

    public TripJSONModel(String userId, DetailList detailList) {
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

    public DetailList getDetailList() {
        return detailList;
    }

    public void setDetailList(DetailList detailList) {
        this.detailList = detailList;
    }
}
