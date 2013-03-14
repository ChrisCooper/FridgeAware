package com.cisc325.g3.fridgeaware;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FoodItemListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item_list);
        
        ListView listview = (ListView) findViewById(R.id.foodlist);
        
        String[] values = new String[100];
        values[0] = "Apple";
        values[1] = "Potatoes";
        values[2] = "Lettuce";
        
        for(int i=3;i<100;i++)
        	values[i] = "";
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_1, android.R.id.text1, values);
        
        listview.setAdapter(adapter);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.food_item_list, menu);
        return true;
    }
    
}
