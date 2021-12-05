package com.example.expensemanagementapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    List<TripDetailModel> allTripList;
    ArrayList<TripDetailModel> allTripArrayList;

    public ListViewAdapter(Context context, List<TripDetailModel> allTripList) {
        mContext = context;
        this.allTripList = allTripList;
        inflater = LayoutInflater.from(mContext);
        this.allTripArrayList = new ArrayList<TripDetailModel>();
        this.allTripArrayList.addAll(allTripList);
    }

    public class ViewTripDetails{
        TextView mTripName,mDescription,mDestination,mStartDate,mEndDate;
    }

    @Override
    public int getCount() {
        return allTripList.size();
    }

    @Override
    public Object getItem(int position) {
        return allTripList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ImageView edit,delete;
        ViewTripDetails details;
        if(view == null){
            details = new ViewTripDetails();
            view = inflater.inflate(R.layout.row,null);
            details.mTripName = view.findViewById(R.id.tripName);
            details.mDestination = view.findViewById(R.id.destination);
            details.mDescription = view.findViewById(R.id.description);
            details.mStartDate = view.findViewById(R.id.startDate);
            details.mEndDate = view.findViewById(R.id.endDate);
            edit = view.findViewById(R.id.editButton);
            delete = view.findViewById(R.id.deleteDelete);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(),AddNewTrip1.class);
                    i.putExtra("tripId",String.valueOf(allTripList.get(position).getId()));
                    mContext.startActivity(i);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(mContext)
                            .setTitle("Do you want to delete"+ allTripList.get(position).getTripName() + "trip ?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DataBaseExecution db = new DataBaseExecution(mContext);
                                    db.deleteTrip(String.valueOf(allTripList.get(position).getId()));
                                    allTripList.remove(position);
                                    notifyDataSetChanged();

                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
                }
            });

            view.setTag(details);
        }
        else{
            details = (ViewTripDetails)view.getTag();
        }
        details.mTripName.setText(allTripList.get(position).getTripName());
        details.mDestination.setText(allTripList.get(position).getDestination());
        details.mDescription.setText(allTripList.get(position).getDescription());
        Date startDate = new Date(Long.valueOf(allTripList.get(position).getStartDate()));
        Date endDate = new Date(Long.valueOf(allTripList.get(position).getEndDate()));
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd MMM yyyy");
        details.mStartDate.setText(simpleFormat.format(startDate));
        details.mEndDate.setText(simpleFormat.format(endDate));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tripId = allTripList.get(position).getId();
            }
        });

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filter(String charText){
        //charText = charText.toLowerCase();
        allTripList.clear();
        if(charText.length() == 0){
            allTripList.addAll(allTripArrayList);

        }
        else{
            List<TripDetailModel> tripExpense = allTripArrayList.stream().filter(s->(s.getTripName().toLowerCase(Locale.getDefault()).contains(charText.toLowerCase(Locale.getDefault()))) ||
                    (s.getDescription().toLowerCase(Locale.getDefault()).contains(charText.toLowerCase(Locale.getDefault()))) ||
                    (s.getDestination().toLowerCase(Locale.getDefault()).contains(charText.toLowerCase(Locale.getDefault())))).collect(Collectors.toList());
            allTripList.addAll(tripExpense);

        }
        notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filterByName(String charText){
        allTripList.clear();
        if(charText.length() == 0){
            allTripList.addAll(allTripArrayList);

        }
        else{
            List<TripDetailModel> tripExpense = allTripArrayList.stream().filter(s->(s.getTripName().toLowerCase(Locale.getDefault()).contains(charText.toLowerCase(Locale.getDefault())))).collect(Collectors.toList());
            allTripList.addAll(tripExpense);

        }
        notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filterByDestination(String charText){
        allTripList.clear();
        if(charText.length() == 0){
            allTripList.addAll(allTripArrayList);

        }
        else{
            List<TripDetailModel> tripExpense = allTripArrayList.stream().filter(s->(s.getDestination().toLowerCase(Locale.getDefault()).contains(charText.toLowerCase(Locale.getDefault())))).collect(Collectors.toList());
            allTripList.addAll(tripExpense);

        }
        notifyDataSetChanged();
    }

}
