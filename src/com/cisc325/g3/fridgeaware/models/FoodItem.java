package com.cisc325.g3.fridgeaware.models;

import java.text.SimpleDateFormat;
import java.util.*;

public class FoodItem {

	private long id;
	private String name;
	private Date expiry;
	private int notificationSetting;
	private int category;
	
	private static SimpleDateFormat date_format = new SimpleDateFormat("MMMM d");
	
	public FoodItem() {
	}
	
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
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNotificationSetting() {
		return notificationSetting;
	}

	public void setNotificationSetting(int notificationSetting) {
		this.notificationSetting = notificationSetting;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
	
}
