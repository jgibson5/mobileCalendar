package com.example.calendarapp;

import android.content.Context;
import android.database.Cursor;
import android.provider.CalendarContract.Calendars;
import android.util.Log;

public class CalendarGetter {
	public static void getCalendars(Context context){
		String[] projection = 
		      new String[]{
		            Calendars._ID, 
		            Calendars.NAME, 
		            Calendars.ACCOUNT_NAME, 
		            Calendars.CALENDAR_DISPLAY_NAME,
		            Calendars.ACCOUNT_TYPE,
		            Calendars.CALENDAR_ACCESS_LEVEL};
		Cursor calCursor = 
		      context.getContentResolver().
		            query(Calendars.CONTENT_URI, 
		                  projection, 
		                  Calendars.VISIBLE + " = 1", 
		                  null, 
		                  Calendars._ID + " ASC");
		if (calCursor.moveToFirst()) {
		   do {
			  for(int i=0; i < 6; i++){
				  String data = calCursor.getString(i);
				  if(data != null){
					  //Log.v(calCursor.getColumnName(i), data);
				  }
			  }
		   } while (calCursor.moveToNext());
		}
	}
}
