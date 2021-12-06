package com.example.expensemanagementapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<TripCardModel> cardModel = new ArrayList();

    public RecyclerViewAdapter(Context mContext, ArrayList<TripCardModel> cardModel) {
        this.mContext = mContext;
        this.cardModel = cardModel;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        PieChart pieChart;
        //ProgressBar p1;
        TextView tripName,tripDest,tripDesc,tripDate;
        Switch isInt,isRisk;

        public void setUpPieChart(){
            pieChart.setDrawHoleEnabled(true);
            pieChart.setUsePercentValues(true);
            pieChart.setEntryLabelTextSize(12);
            pieChart.setEntryLabelTextSize(Color.BLACK);
            pieChart.setCenterText("Expenses Ratio");
            pieChart.setCenterTextSize(24);
            pieChart.getDescription().setEnabled(false);
            pieChart.animateY(5000, Easing.EaseOutExpo);

            Legend l = pieChart.getLegend();
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            l.setOrientation(Legend.LegendOrientation.VERTICAL);
            l.setDrawInside(false);
            l.setEnabled(true);
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pieChart = itemView.findViewById(R.id.pieChart);
            setUpPieChart();
            //p1 = itemView.findViewById(R.id.totalCompBar);
            //header = itemView.findViewById(R.id.tripNameText);
            tripName = itemView.findViewById(R.id.tripNameText);
            tripDest = itemView.findViewById(R.id.tripDestText);
            tripDesc = itemView.findViewById(R.id.tripDescText);
            tripDate = itemView.findViewById(R.id.tripDateText);
            isInt = itemView.findViewById(R.id.isIntSwitch);
            isRisk = itemView.findViewById(R.id.isRiskSwitch);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_trips_card_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TripCardModel model = cardModel.get(position);
        holder.pieChart.setData(model.getPieChart());
       // holder.p1.setProgress(model.getProgress1());
       // holder.header.setText(model.getTextView());
        holder.tripName.setText(model.getTripName());
        holder.tripDest.setText(model.getTripDest());
        holder.tripDesc.setText(model.getTripDesc());
        holder.tripDate.setText(model.getTripDate());
        holder.isInt.setChecked(model.getIsInt());
        holder.isRisk.setChecked(model.getIsRisk());
    }
    @Override
    public int getItemCount() {
        return cardModel.size();
    }
}
