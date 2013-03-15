package com.cisc325.g3.fridgeaware.models;

import java.util.ArrayList;

public class FoodItem {
	
	public static ArrayList<FoodItem> values = new ArrayList<FoodItem>();

	private String name;
	
	public FoodItem(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return new String(name);
	}
	
}
