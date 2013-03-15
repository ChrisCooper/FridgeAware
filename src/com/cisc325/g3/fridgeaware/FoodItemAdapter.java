package com.cisc325.g3.fridgeaware;

import java.util.ArrayList;

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
    ArrayList<FoodItem> foodItems;
    
    public FoodItemAdapter(Context context, int layoutResourceId, ArrayList<FoodItem> foodItems) {
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
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            
            row.setTag(holder);
        }
        else
        {
            holder = (FoodItemHolder)row.getTag();
        }
        
        FoodItem foodItem = foodItems.get(position);
        holder.txtTitle.setText(foodItem.getName());
        
        //Use this line to set the picture later
        //holder.imgIcon.setImageResource(foodItem.icon);
        
        return row;
    }
    
    static class FoodItemHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
    }
}