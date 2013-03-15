package com.cisc325.g3.fridgeaware;

import com.cisc325.g3.fridgeaware.models.*;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
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
        
        adapter = new ArrayAdapter<FoodItem>(this,
        		android.R.layout.simple_list_item_1, android.R.id.text1, FoodItem.values);
        
        listview.setAdapter(adapter);
        
        //Attach Add Item Button Listener...
        Button addItemButton = (Button) findViewById(R.id.button_add_item);
        
        addItemButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(FoodItemListActivity.this, AddItemActivity.class);
				
				startActivity(intent);
				
			}
		});
        
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
    
}
