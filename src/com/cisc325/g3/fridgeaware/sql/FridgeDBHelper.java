package com.cisc325.g3.fridgeaware.sql;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class FridgeDBHelper extends SQLiteOpenHelper {
	

	private static final String DATABASE_NAME = "fridgeaware.db";
	private static final int DATABASE_VERSION = 1;
	
	public static final String FOODITEM_TABLE_NAME = "fooditems";
	public static final String FOODITEM_COLUMN_ID = "_id";
	public static final String FOODITEM_COLUMN_NAME = "name";
	public static final String FOODITEM_COLUMN_EXPIRY = "expiry";
	public static final String FOODITEM_COLUMN_NOTIFICATION_SETTING = "notification_setting";
	public static final String FOODITEM_COLUMN_CATEGORY = "category";
	
	
	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
	    + FOODITEM_TABLE_NAME + "("
	    	+ FOODITEM_COLUMN_ID + " integer primary key autoincrement, "
		    + FOODITEM_COLUMN_NAME + " text not null, "
		    + FOODITEM_COLUMN_EXPIRY + " text not null, "
		    + FOODITEM_COLUMN_NOTIFICATION_SETTING + " integer not null, "
		    + FOODITEM_COLUMN_CATEGORY + " integer not null"
	    + ");";

	
	public FridgeDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(FridgeDBHelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + FOODITEM_TABLE_NAME);
		    onCreate(db);
	}

}
