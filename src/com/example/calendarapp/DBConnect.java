package com.example.calendarapp;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

public class DBConnect extends SQLiteOpenHelper {
	 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "todoManager";
 
    // Todo table name
    private static final String TABLE_TODOS = "todos";
 
    // Todo Table Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_TASK = "task";
    private static final String KEY_DATE = "date";
    private static final String KEY_HARDNESS = "hardness";
    private static final String KEY_TIME_REQ = "time_req";
    public static final String START_DATE = "0000/00/00 00:00";
    public static final String END_DATE = "9999/99/99 99:99";
 
    public DBConnect(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TODOS_TABLE = "CREATE TABLE " + TABLE_TODOS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TASK + " TEXT,"
                + KEY_DATE + " TEXT," + KEY_HARDNESS + " int," + KEY_TIME_REQ + " int" + ")";
        db.execSQL(CREATE_TODOS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODOS);
 
        // Create tables again
        onCreate(db);
    }
    
    
    /**
     * Add a single Todo to the database.
     * @param todo
     */
    void addTodo(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_TASK, todo.getTodo());
        values.put(KEY_DATE, todo.getDate().toString());
        values.put(KEY_HARDNESS, todo.getHardness());
        values.put(KEY_TIME_REQ, todo.getTime_req());
 
        // Inserting Row
        db.insert(TABLE_TODOS, null, values);
        db.close(); // Closing database connection
    }
    
    /**
     * Retrieve a single Todo from database whose description matches task.
     * @param task
     * @return
     */
    public Todo getTodo(String task) {
        SQLiteDatabase db = this.getReadableDatabase();
        //Submit query and get cursor.
        Cursor cursor = db.query(TABLE_TODOS, new String[] { KEY_ID,
                KEY_TASK, KEY_DATE, KEY_HARDNESS, KEY_TIME_REQ }, KEY_TASK + "=?",
                new String[] { task }, null, null, KEY_DATE + " ASC");
        //Move to first item in cursor.
        if (cursor != null)
            cursor.moveToFirst();
        // Make Todo object from cursor entry.
        Todo todo = new Todo(cursor.getString(1), cursor.getString(2));
        return todo;
    }
    
    /**
     * Get all todos whose description matches task.
     * @param task
     * @return
     */
    public ArrayList<Todo> getTodos(String task) {
    	SQLiteDatabase db = this.getReadableDatabase();
    	//Submit query and get cursor.
        Cursor cursor = db.query(TABLE_TODOS, new String[] { KEY_ID,
                KEY_TASK, KEY_DATE, KEY_HARDNESS, KEY_TIME_REQ }, KEY_TASK + "=?",
                new String[] { task }, null, null, KEY_DATE + " ASC");
        return convertCursorToArrayList(cursor);       
    }
    
    /**
     * Get all todos from the database between the provided start and end times.
     * @param start
     * @param end
     * @return
     */
    public ArrayList<Todo> getAllTodos(String start, String end){
    	SQLiteDatabase db = this.getReadableDatabase();
    	//Submit query and get cursor.
        Cursor cursor = db.query(TABLE_TODOS, new String[] { KEY_ID,
                KEY_TASK, KEY_DATE, KEY_HARDNESS, KEY_TIME_REQ }, KEY_DATE + ">\"" + start + 
                	"\" AND " + KEY_DATE + "<\"" + end + "\"", 
                		null, null, null, KEY_DATE + " ASC");
        return convertCursorToArrayList(cursor);  
    }

    
    /**
     * Convert a cursor over Todo items to an iterable ArrayList.
     * @param c
     * @return
     */
    public ArrayList<Todo> convertCursorToArrayList(Cursor c) {
    	ArrayList<Todo> list = new ArrayList<Todo>();
    	//Iterate through each item in cursor.
    	for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
    		//Add new todo item to list.
    		list.add(new Todo(c.getString(1), c.getString(2), c.getInt(3), c.getInt(4)));
    	}
    	return list;
    }
    
    
}
