package com.cisc325.g3.fridgeaware;

import java.util.Date;
import java.util.List;

import com.cisc325.g3.fridgeaware.models.*;
import com.cisc325.g3.fridgeaware.sql.FoodItemDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

public class FoodItemListActivity extends Activity {

	private ArrayAdapter<FoodItem> adapter;
	private FoodItemDataSource datasource;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item_list);
        
        Button addItemButton = (Button) findViewById(R.id.button_add_item);
        
        addItemButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(FoodItemListActivity.this, AddItemActivity.class);
				startActivityForResult(intent, 0);
				
			}
		});
        
        ListView lv = (ListView) findViewById(R.id.foodlist);
        
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		
        		FoodItemAdapter.FoodItemHolder h = (FoodItemAdapter.FoodItemHolder) view.getTag();
        		long foodItemID = h.foodItem.getId();
        		
        		Intent intent = new Intent(FoodItemListActivity.this, EditItemActivity.class);
        		intent.putExtra("ID", foodItemID);
        		
        		startActivityForResult(intent, 1);
        		
        	}
        	
		});
        
        dBUpdateArrayAdapter();
        
    }
    
    @Override
    public void onResume() {
    	
    	//dBUpdateArrayAdapter();
    	
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

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			
			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean onQueryTextChange(String newText) {
				
				FoodItemDataSource datasource = new FoodItemDataSource(FoodItemListActivity.this);
		  		datasource.open();
		  		
		  		List<FoodItem> items = datasource.searchFoodItems(newText);
		  		
		  		datasource.close();
		          
		        searchUpdateArrayAdapter(items);
				
				return false;
			}
		});
        
        return true;
        
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	//Attach Add Item Button Listener...
    	switch(item.getItemId()) {
    	
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
    
    @Override
    public void onNewIntent(Intent intent) {
    	
    	//setIntent(intent);
    	//searchFoodItems(intent);
    	
  	}

  	private void searchFoodItems(Intent intent) {
  		
  		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
  			
    	    String query = intent.getStringExtra(SearchManager.QUERY);
  		
	  		FoodItemDataSource datasource = new FoodItemDataSource(this);
	  		datasource.open();
	  		
	  		List<FoodItem> items = datasource.searchFoodItems(query);
	  		
	  		datasource.close();
	          
	        searchUpdateArrayAdapter(items);
        
  		}
  		
  	}
    
}
