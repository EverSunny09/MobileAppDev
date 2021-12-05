package com.example.expensemanagementapplication;

import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class ExpenseCardModel {
    ImageView image;
    String text;

    public ExpenseCardModel(String text) {
        //this.image = image;
        this.text = text;
        setImage(text);
    }

    public ImageView getImage() { return image; }

    public void setImage(ImageView image) { this.image = image; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    private void setImage(String text){
        switch (text){
            case "Food":
                //do food image;
                break;
            case "Accommodation":
                break;
            case "Travel":
                break;
        }
    }
}
