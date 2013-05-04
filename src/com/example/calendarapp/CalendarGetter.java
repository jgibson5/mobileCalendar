package com.example.calendarapp;

import android.content.Context;
import android.database.Cursor;
import android.provider.CalendarContract.Calendars;

public class CalendarGetter {
	
	
	/**
	 * @param context
	 * @return Cursor over all available calendars.
	 */
	public static Cursor getCalendars(Context context){
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
		return calCursor;
	}
}
