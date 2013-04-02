package com.cisc325.g3.fridgeaware;

import java.util.List;

import com.cisc325.g3.fridgeaware.models.FoodItem;
import com.cisc325.g3.fridgeaware.sql.FoodItemDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.widget.CursorAdapter;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class SearchableActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    //setContentView(R.layout.search);

	    // Get the intent, verify the action and get the query
	    Intent intent = getIntent();
	    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
	      String query = intent.getStringExtra(SearchManager.QUERY);
	      searchFoodItems(query);
	    }
	}


	private void searchFoodItems(String query) {
		
		FoodItemDataSource datasource = new FoodItemDataSource(this);
		datasource.open();
		
		List<FoodItem> items = datasource.searchFoodItems(query);
		
		datasource.close();
        
        ((FoodItemListActivity) getParent()).searchUpdateArrayAdapter(items);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_searchable, menu);
		return true;
	}

}
