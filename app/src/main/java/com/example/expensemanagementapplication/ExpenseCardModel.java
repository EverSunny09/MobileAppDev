package com.example.expensemanagementapplication;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class ExpenseCardModel {
    String expType;
    String expTime;
    String expCom;
    String expAmt;

    public ExpenseCardModel(String expType, String expTime, String expAmt, String expCom) {
        this.expType = expType;
        this.expTime = expTime;
        this.expCom = expCom;
        this.expAmt = expAmt;
    }

    public String getExpType() {
        return expType;
    }

    public void setExpType(String expType) {
        this.expType = expType;
    }

    public String getExpTime() {
        return expTime;
    }

    public void setExpTime(String expTime) {
        this.expTime = expTime;
    }

    public String getExpCom() {
        return expCom;
    }

    public void setExpCom(String expCom) {
        this.expCom = expCom;
    }

    public String getExpAmt() {
        return expAmt;
    }

    public void setExpAmt(String expAmt) {
        this.expAmt = expAmt;
    }
}
