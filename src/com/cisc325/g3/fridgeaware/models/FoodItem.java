package com.cisc325.g3.fridgeaware.models;

import java.text.SimpleDateFormat;
import java.util.*;

public class FoodItem {
	
	public static ArrayList<FoodItem> values = new ArrayList<FoodItem>();

	private String name;
	private Date expiry;
	
	private static SimpleDateFormat date_format = new SimpleDateFormat("MMMM d");
	
	public FoodItem(String name) {
		this.name = name;
		this.expiry = new Date();
	}
	
	public FoodItem(String name, Date expiry) {
		this.name = name;
		this.expiry = expiry;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDate() {
		return expiry;
	}
	
	public String getDateString() {
		return date_format.format(expiry);
	}
	
	public void setDate(Date expiry) {
		this.expiry = expiry;
	}
	
	public String toString() {
		return new String(name);
	}
	
}
