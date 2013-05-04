package com.example.calendarapp;

import java.text.DecimalFormat;
import java.math.RoundingMode;
import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class EventGetter {
	
	private static final long time = System.currentTimeMillis();
	
	
	public static ArrayList<Placeable> getEvents(Context context){
		DecimalFormat format = new DecimalFormat("######0000");
		format.setRoundingMode(RoundingMode.FLOOR);
		String s = format.format(time);
		long startDate = Long.parseLong(s);
		return EventGetter.getEvents(context, startDate);
	}
	
	public static ArrayList<Placeable> getEvents(Context context, long startDate){
		
		long endDate = startDate + 86400000;
		
		Cursor cursor = context.getContentResolver().query(
				Uri.parse("content://com.android.calendar/events"),
                new String[] { "title", "dtstart", "dtend", "duration"}, 
                	"dtstart>="  + startDate + " and dtend<=" + endDate, 
                		null, "dtstart ASC");
		ArrayList<Placeable> list = new ArrayList<Placeable>();
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			long start = cursor.getLong(1);
			long end = cursor.getLong(2);
			String duration = cursor.getString(3);
			String description = cursor.getString(0);
			if(start >= startDate && description!=null){
				if (end <= endDate){
					list.add(new Event(start, end, description));
				}else{
					list.add(new Event(start, duration, description));
				}
			}
		}
		return list;
	}
}
