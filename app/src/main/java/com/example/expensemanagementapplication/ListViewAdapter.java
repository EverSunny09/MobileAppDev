package com.example.expensemanagementapplication;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
        ViewTripDetails details;
        if(view == null){
            details = new ViewTripDetails();
            view = inflater.inflate(R.layout.row,null);
            details.mTripName = view.findViewById(R.id.tripName);
            details.mDestination = view.findViewById(R.id.destination);
            details.mDescription = view.findViewById(R.id.description);
            details.mStartDate = view.findViewById(R.id.startDate);
            details.mEndDate = view.findViewById(R.id.endDate);

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

}
