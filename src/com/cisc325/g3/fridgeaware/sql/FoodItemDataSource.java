package com.cisc325.g3.fridgeaware.sql;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.cisc325.g3.fridgeaware.models.FoodItem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class FoodItemDataSource {
	private SQLiteDatabase database;
	private FridgeDBHelper dbHelper;
	private String[] allColumns = { FridgeDBHelper.FOODITEM_COLUMN_ID,
			FridgeDBHelper.FOODITEM_COLUMN_NAME,
			FridgeDBHelper.FOODITEM_COLUMN_EXPIRY,
			FridgeDBHelper.FOODITEM_COLUMN_NOTIFICATION_SETTING,
			FridgeDBHelper.FOODITEM_COLUMN_CATEGORY};
	
	public FoodItemDataSource(Context context) {
		dbHelper = new FridgeDBHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}

	public List<FoodItem> getAllFoodItems() {
		
		List<FoodItem> items = new ArrayList<FoodItem>();

	    Cursor cursor = database.query(FridgeDBHelper.FOODITEM_TABLE_NAME,
	    		allColumns, null, null, null, null, FridgeDBHelper.FOODITEM_COLUMN_EXPIRY);
	    
	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	FoodItem item = cursorToFoodItem(cursor);
	    	items.add(item);
	    	cursor.moveToNext();
	    }
	    
	    // Make sure to close the cursor
	    cursor.close();
	    
	    return items;
	}
	
	public FoodItem createFoodItem(String name, Date expiryDate, int notificationSetting, int category) {
	    ContentValues values = new ContentValues();
	    
	    values.put(FridgeDBHelper.FOODITEM_COLUMN_NAME, name);
	    values.put(FridgeDBHelper.FOODITEM_COLUMN_EXPIRY, expiryDate.toString());
	    values.put(FridgeDBHelper.FOODITEM_COLUMN_NOTIFICATION_SETTING, notificationSetting);
	    values.put(FridgeDBHelper.FOODITEM_COLUMN_CATEGORY, category);
	    
	    long insertId = database.insertOrThrow(FridgeDBHelper.FOODITEM_TABLE_NAME, null,
	        values);
	    
	    // Get the item we just inserted so we can return it
	    Cursor cursor = database.query(FridgeDBHelper.FOODITEM_TABLE_NAME,
	        allColumns, FridgeDBHelper.FOODITEM_COLUMN_ID + " = " + insertId, null,
	        null, null, null);
	    
	    cursor.moveToFirst();
	    FoodItem newItem = cursorToFoodItem(cursor);
	    
	    cursor.close();
	    
	    return newItem;
	  }

	  public void deleteFoodItem(FoodItem item) {
	    long id = item.getId();
	    System.out.println("Food item deleted with id: " + id);
	    database.delete(FridgeDBHelper.FOODITEM_TABLE_NAME, FridgeDBHelper.FOODITEM_COLUMN_ID
	        + " = " + id, null);
	  }
	

	private FoodItem cursorToFoodItem(Cursor cursor) {
		FoodItem item = new FoodItem();
		item.setId(cursor.getLong(0));
		item.setName(cursor.getString(1));
		item.setDate(new Date(cursor.getLong(2)));
		item.setNotificationSetting(cursor.getInt(3));
		item.setCategory(cursor.getInt(4));
		
	    return item;
	}
}
