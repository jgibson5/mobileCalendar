package com.example.calendarapp;

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
 
    // Contacts table name
    private static final String TABLE_TODOS = "todos";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_TASK = "task";
    private static final String KEY_DATE = "date";
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
                + KEY_DATE + " TEXT" + ")";
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
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new contact
    void addTodo(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_TASK, todo.getTodo()); // Contact Name
        values.put(KEY_DATE, todo.getDate().toString()); // Contact Phone
 
        // Inserting Row
        db.insert(TABLE_TODOS, null, values);
        db.close(); // Closing database connection
    }
    
    public Todo getTodo(String task) {
        SQLiteDatabase db = this.getReadableDatabase();
     
        Cursor cursor = db.query(TABLE_TODOS, new String[] { KEY_ID,
                KEY_TASK, KEY_DATE }, KEY_TASK + "=?",
                new String[] { task }, null, null, KEY_DATE + " ASC");
        if (cursor != null)
            cursor.moveToFirst();
     
        Todo todo = new Todo(cursor.getString(1), cursor.getString(2));
        // return contact
        return todo;
    }
    
    public Cursor getTodos(String task) {
    	SQLiteDatabase db = this.getReadableDatabase();
        
        Cursor cursor = db.query(TABLE_TODOS, new String[] { KEY_ID,
                KEY_TASK, KEY_DATE }, KEY_TASK + "=?",
                new String[] { task }, null, null, KEY_DATE + " ASC");
        return cursor;       
    }
    
    public Cursor getAllTodos(String start, String end){
    	SQLiteDatabase db = this.getReadableDatabase();
        
        Cursor cursor = db.query(TABLE_TODOS, new String[] { KEY_ID,
                KEY_TASK, KEY_DATE }, KEY_DATE + ">\"" + start + 
                	"\" AND " + KEY_DATE + "<\"" + end + "\"", 
                		null, null, null, KEY_DATE + " ASC");
        return cursor;  
    }
    
    
    
}
