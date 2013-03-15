package com.cisc325.g3.fridgeaware;

import com.cisc325.g3.fridgeaware.models.*;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class FoodItemListActivity extends Activity {

	private ArrayAdapter<FoodItem> adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item_list);
        
        //Populate ListView...
        ListView listview = (ListView) findViewById(R.id.foodlist);
        
        FoodItem.values.add(new FoodItem("Apple"));
        FoodItem.values.add(new FoodItem("Potatoes"));
        FoodItem.values.add(new FoodItem("Lettuce"));
        
        adapter = new FoodItemAdapter(this,
        		R.layout.food_item_cell, FoodItem.values);
        
        listview.setAdapter(adapter);
        
        
    }
    
    @Override
    public void onResume() {
    	
    	adapter.notifyDataSetChanged();
    	
    	super.onResume();
    	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.food_item_list, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	//Attach Add Item Button Listener...
    	switch(item.getItemId()) {
    	
	    	case R.id.action_add_item:
	    		Intent intent = new Intent(FoodItemListActivity.this, AddItemActivity.class);
				startActivity(intent);
	    		return true;
	    	default:
	    		return super.onOptionsItemSelected(item);
    	
    	}
    	
    }
    
}
