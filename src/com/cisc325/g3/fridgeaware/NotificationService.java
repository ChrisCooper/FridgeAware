package com.cisc325.g3.fridgeaware;

import java.util.Date;
import java.util.Calendar;

import com.cisc325.g3.fridgeaware.models.FoodItem;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class NotificationService {
    private Context context;
    private PendingIntent mAlarmSender;
    public NotificationService(Context context) {
        this.context = context;
    }

    public void scheduleExpiryWarning(Date date, FoodItem foodItem){
    	
    	Intent intent = new Intent(context, NotificationReceiver.class);
        intent.putExtra("foodItemID", foodItem.getId());
        mAlarmSender = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        
        Date now = new Date();
        
        date.setHours(now.getHours());
        date.setMinutes(now.getMinutes());
        date.setSeconds(now.getSeconds()+15);
        
        long notificationTime = date.getTime();       
        
        // Schedule the alarm!
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, notificationTime, mAlarmSender);
    }
}