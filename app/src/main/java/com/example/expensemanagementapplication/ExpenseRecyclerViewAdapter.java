package com.example.expensemanagementapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        holder.image.setImageResource(R.drawable.food);
        holder.text.setText(model.getText());
    }

    @Override
    public int getItemCount() {return cardModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);
            text = itemView.findViewById(R.id.textViewExp);
        }
    }

}

