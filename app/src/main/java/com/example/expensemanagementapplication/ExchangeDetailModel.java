package com.example.expensemanagementapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class ExchangeDetailModel implements Parcelable {
    private int exchangeId;
    private int userId;
    private String type;
    private String data;

    public ExchangeDetailModel() {
    }

    public ExchangeDetailModel(int exchangeId, int userId, String type, String data) {
        this.exchangeId = exchangeId;
        this.userId = userId;
        this.type = type;
        this.data = data;
    }

    protected ExchangeDetailModel(Parcel in) {
        exchangeId = in.readInt();
        userId = in.readInt();
        type = in.readString();
        data = in.readString();
    }

    public static final Creator<ExchangeDetailModel> CREATOR = new Creator<ExchangeDetailModel>() {
        @Override
        public ExchangeDetailModel createFromParcel(Parcel in) {
            return new ExchangeDetailModel(in);
        }

        @Override
        public ExchangeDetailModel[] newArray(int size) {
            return new ExchangeDetailModel[size];
        }
    };

    public int getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(int exchangeId) {
        this.exchangeId = exchangeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(exchangeId);
        dest.writeInt(userId);
        dest.writeString(type);
        dest.writeString(data);
    }
}
