package com.example.expensemanagementapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;

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
        /*ProgressBar p1;
        ProgressBar p2;
        ProgressBar p3;*/

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            /*pieChart = itemView.findViewById(R.id.pieChart);
            p1 = itemView.findViewById(R.id.progressBar1);
            p2 = itemView.findViewById(R.id.progressBar2);
            p3 = itemView.findViewById(R.id.progressBar3);*/

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
        holder.pieChart.setData(model.getPiechart());
    }


    @Override
    public int getItemCount() {
        return cardModel.size();
    }

}
