package com.cisc325.g3.fridgeaware.models;

import java.util.*;

public class FoodItem {
	
	public static ArrayList<FoodItem> values = new ArrayList<FoodItem>();

	private String name;
	private Date expiry;
	
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
	
	public void setDate(Date expiry) {
		this.expiry = expiry;
	}
	
	public String toString() {
		return new String(name);
	}
	
}
