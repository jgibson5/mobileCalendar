package com.example.calendarapp;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

public class EventDB extends SQLiteOpenHelper {
	 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "eventManager";
 
    // Contacts table name
    private static final String TABLE_EVENTS = "events";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_START = "task";
    private static final String KEY_END = "date";
    private static final String KEY_DESCRIPTION = "desciption";
    public static final String START_DATE = "0000/00/00 00:00";
    public static final String END_DATE = "9999/99/99 99:99";
 
    /**
     * Create new EventDB.
     * @param context
     */
    public EventDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_START + " TEXT,"
                + KEY_END + " TEXT," + KEY_DESCRIPTION + " TEXT," + ")";
        db.execSQL(CREATE_EVENTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
 
        // Create tables again
        onCreate(db);
    }
    
    
 
  
    /**
     * Add single event to database.
     * @param event
     */
    void addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_START, event.getStart().toString());
        values.put(KEY_END, event.getStart().toString());
        values.put(KEY_DESCRIPTION, event.getDescription());
 
        // Inserting Row
        db.insert(TABLE_EVENTS, null, values);
        db.close(); // Closing database connection
    }
    
    
    /**
     * Retrieve single event whose description matches task.
     * @param task
     * @return
     */
    public Event getEvent(String task) {
        SQLiteDatabase db = this.getReadableDatabase();
     
        Cursor cursor = db.query(TABLE_EVENTS, new String[] { KEY_ID,
                KEY_START, KEY_END, KEY_DESCRIPTION}, KEY_START + "=?",
                new String[] { task }, null, null, KEY_END + " ASC");
        if (cursor != null)
            cursor.moveToFirst();
        Event event = new Event(cursor.getLong(1), cursor.getLong(2), 
        		cursor.getString(3) );
        // return event
        return event;
    }
    
    /**
     * Retrieve all events whose description matches task.
     * @param task
     * @return
     */
    public ArrayList<Event> getEvents(String task) {
    	SQLiteDatabase db = this.getReadableDatabase();
        
        Cursor cursor = db.query(TABLE_EVENTS, new String[] { KEY_ID,
                KEY_START, KEY_END, KEY_DESCRIPTION}, KEY_START + "=?",
                new String[] { task }, null, null, KEY_END + " ASC");
        return convertCursorToArrayList(cursor);       
    }
    
    /**
     * Retreive all events in the database that fall between given times.
     * @param start
     * @param end
     * @return
     */
    public ArrayList<Event> getAllEvents(String start, String end){
    	SQLiteDatabase db = this.getReadableDatabase();
        
        Cursor cursor = db.query(TABLE_EVENTS, new String[] { KEY_ID,
                KEY_START, KEY_END, KEY_DESCRIPTION}, KEY_END + ">\"" + start + 
                	"\" AND " + KEY_END + "<\"" + end + "\"", 
                		null, null, null, KEY_END + " ASC");
        return convertCursorToArrayList(cursor);  
    }
    
    /**
     * Convert event cursor into ArrayList of events.
     * @param c
     * @return
     */
    public ArrayList<Event> convertCursorToArrayList(Cursor c) {
    	ArrayList<Event> list = new ArrayList<Event>();
    	for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
    		list.add(new Event(c.getLong(1), c.getLong(2), c.getString(3)));
    	}
    	return list;
    }
    
    
}
