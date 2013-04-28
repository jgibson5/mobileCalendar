package com.example.calendarapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract.Calendars;
import android.util.Log;

public class EventGetter {
	public static void getEvents(Context context){
		String[] projection = 
			      new String[]{
			            Calendars._ID, 
			            Calendars.NAME, 
			            Calendars.ACCOUNT_NAME, 
			            Calendars.ACCOUNT_TYPE};
			Cursor calCursor = 
			      context.getContentResolver().
			            query(Calendars.CONTENT_URI, 
			                  projection, 
			                  Calendars.VISIBLE + " = 1", 
			                  null, 
			                  Calendars._ID + " ASC");
			if (calCursor.moveToFirst()) {
			   do {
			      String id = calCursor.getString(0);
			      String accountType = calCursor.getString(1);
			      if (accountType != null){
			    	  Log.v("id", id);
			    	  Log.v("type", accountType);
			      }
			      //
			   } while (calCursor.moveToNext());
			}
			           
			Cursor cursor = context.getContentResolver().query(
					Uri.parse("content://com.android.calendar/events"),
	                new String[] { "calendar_id", "title", "description",
	            "dtstart", "dtend", "eventLocation" }, "calendar_id=" + 7, 
	            null, "dtstart ASC");
			Log.v("size", ""+cursor.getCount());
			if(cursor.moveToNext()){
				do {
					String id = cursor.getString(0);
					if (id.contains("7")){
						String title = cursor.getString(2);
						String start = getDate(Long.parseLong(cursor.getString(3)));
						String end = getDate(Long.parseLong(cursor.getString(4)));
						if (title != null){
							Log.v("title", title+ " "+ start + " " + end);
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
