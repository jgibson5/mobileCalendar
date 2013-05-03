package com.example.calendarapp;

import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.math.RoundingMode;
import java.util.Calendar;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class EventGetter {
	
	private static final long time = System.currentTimeMillis();
	
	
	public static ArrayList<Event> getEvents(Context context, int cal_id){
		DecimalFormat format = new DecimalFormat("######0000");
		format.setRoundingMode(RoundingMode.FLOOR);
		String s = format.format(time);
		long startDate = Long.parseLong(s);
		return EventGetter.getEvents(context, cal_id, startDate);
	}
	
	public static ArrayList<Event> getEvents(Context context, int cal_id, long startDate){
		
		long endDate = startDate + 86400000;
		
		Cursor cursor = context.getContentResolver().query(
				Uri.parse("content://com.android.calendar/events"),
                new String[] { "calendar_id", "title", "description",
            "dtstart", "dtend", "duration", "eventLocation" }, "dtstart<="  + startDate + " and dtend>=" + endDate, 
            null, "dtstart ASC");
		ArrayList<Event> list = new ArrayList<Event>;
		
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			long start = cursor.getLong();
			long end = cursor.getLong();
			String duration = cursor.getString();
			String description = cursor.getString();
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
