package com.cisc325.g3.fridgeaware;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cisc325.g3.fridgeaware.models.*;

public class FoodItemAdapter extends ArrayAdapter<FoodItem>{

    Context context; 
    int layoutResourceId;
    List<FoodItem> foodItems;
    
    public FoodItemAdapter(Context context, int layoutResourceId, List<FoodItem> foodItems) {
        super(context, layoutResourceId, foodItems);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.foodItems = foodItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        FoodItemHolder holder = null;
        
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new FoodItemHolder();
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            holder.expiryTitle = (TextView)row.findViewById(R.id.expiryTitle);
            
            row.setTag(holder);
        }
        else
        {
            holder = (FoodItemHolder)row.getTag();
        }
        
        FoodItem foodItem = foodItems.get(position);
        holder.txtTitle.setText(foodItem.getName());
        holder.expiryTitle.setText(foodItem.getDateString());
        holder.foodItem = foodItem;
        
        return row;
    }
    
    public static class FoodItemHolder
    {
        TextView txtTitle;
        TextView expiryTitle;
        FoodItem foodItem;
    }
}