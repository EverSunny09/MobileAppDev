package com.example.expensemanagementapplication;

import java.util.ArrayList;
import java.util.List;

public class TripJSONModel {
    private String userId;
    private List<DetailList> detailList;

    public TripJSONModel(String userId, List<DetailList> detailList) {
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

    public List<DetailList> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<DetailList> detailList) {
        this.detailList = detailList;
    }
}
