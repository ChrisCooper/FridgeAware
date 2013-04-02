package com.cisc325.g3.fridgeaware;

import java.util.List;

import com.cisc325.g3.fridgeaware.models.*;
import com.cisc325.g3.fridgeaware.sql.FoodItemDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

public class FoodItemListActivity extends Activity {

	private ArrayAdapter<FoodItem> adapter;
	private FoodItemDataSource datasource;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item_list);
        
        dBUpdateArrayAdapter();
        
    }
    
    @Override
    public void onResume() {
    	
    	dBUpdateArrayAdapter();
    	
    	super.onResume();
    	
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
    	dBUpdateArrayAdapter();
    	
    	super.onActivityResult(requestCode, resultCode, data);
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.food_item_list, menu);
        
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	//Attach Add Item Button Listener...
    	switch(item.getItemId()) {
    	
	    	//case R.id.action_add_item:
	    	//	Intent intent = new Intent(FoodItemListActivity.this, AddItemActivity.class);
			//	startActivityForResult(intent, 0);
	    	//	return true;
	    	default:
	    		return super.onOptionsItemSelected(item);
    	
    	}
    	
    }
    
    public ArrayAdapter<FoodItem> getFoodItemAdapter() {
    	
    	return adapter;
    	
    }
    
    public void searchUpdateArrayAdapter(List<FoodItem> items) {
    	
    	ListView listview = (ListView) findViewById(R.id.foodlist);
    	
    	adapter = new FoodItemAdapter(this,
        		R.layout.food_item_cell, items);
    	
        listview.setAdapter(adapter);
    	
    }
    
    private void dBUpdateArrayAdapter() {
    	
    	datasource = new FoodItemDataSource(this);
        datasource.open();
    	
    	List<FoodItem> items = datasource.getAllFoodItems();
        
        //Populate ListView...
        ListView listview = (ListView) findViewById(R.id.foodlist);
        
        adapter = new FoodItemAdapter(this,
        		R.layout.food_item_cell, items);
    	
        listview.setAdapter(adapter);
    	
    	datasource.close();
    	
    }
    
}
