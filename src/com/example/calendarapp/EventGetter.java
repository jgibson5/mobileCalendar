package com.example.calendarapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class EventGetter {
	public static void getEvents(Context context, int cal_id){           
		Cursor cursor = context.getContentResolver().query(
				Uri.parse("content://com.android.calendar/events"),
                new String[] { "calendar_id", "title", "description",
            "dtstart", "dtend", "duration", "eventLocation" }, "calendar_id=" + cal_id, 
            null, "dtstart ASC");
		Log.v("size", ""+cursor.getCount());
		if(cursor.moveToNext()){
			do {
				for (int i =0; i<7;i++){
					String thing = cursor.getString(i);
					if (thing != null){
						//Log.v(cursor.getColumnName(i), thing);
					}
				}
			} while (cursor.moveToNext());
		}
	}
	
	public static String getDate(long milliSeconds) {
	    SimpleDateFormat formatter = new SimpleDateFormat(
	            "yyyy/MM/dd hh:mm");
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTimeInMillis(milliSeconds);
	    return formatter.format(calendar.getTime());
	}
}
