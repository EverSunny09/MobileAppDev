package com.example.expensemanagementapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpenseRecyclerViewAdapter extends RecyclerView.Adapter<ExpenseRecyclerViewAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<ExpenseCardModel> cardModel = new ArrayList();

    public ExpenseRecyclerViewAdapter(ArrayList<ExpenseCardModel> cardModel, Context mContext) {
        this.mContext = mContext;
        this.cardModel = cardModel;
    }

    @NonNull
    @Override
    public ExpenseRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_expenses_card_view,parent,false);
        return new ExpenseRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseRecyclerViewAdapter.ViewHolder holder, int position) {
        ExpenseCardModel model = cardModel.get(position);
        holder.expType.setText(model.getExpType());
        holder.expTime.setText(model.getExpTime());
        holder.expAmt.setText(model.getExpAmt());
        holder.expCom.setText(model.getExpCom());

        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(),android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);
    }


    @Override
    public int getItemCount() {return cardModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView expType, expAmt, expTime, expCom;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            expType = itemView.findViewById(R.id.expType);
            expAmt = itemView.findViewById(R.id.expAmt);
            expTime = itemView.findViewById(R.id.expTime);
            expCom = itemView.findViewById(R.id.expCom);
        }
    }

}

