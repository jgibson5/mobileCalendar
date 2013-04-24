package com.example.calendarapp;

import java.util.ArrayList;

import com.example.calendarapp.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DBConnect dbc = new DBConnect(this);
//		Todo t1 = new Todo("Brief Android, 2013/04/16 15:00");
//		Todo t2 = new Todo("Brief Android, 2013/04/14 15:00");
//		Todo t3 = new Todo("Brief Android, 2013/06/14 15:00");
//		dbc.addTodo(t1);
//		dbc.addTodo(t2);
//		dbc.addTodo(t3);
//		ArrayList<String> todoList = new ArrayList<String>();
//		todoList.add(t1.toString());
//		todoList.add(t2.toString());
//		todoList.add(t3.toString());
		
//		Cursor c = dbc.getAllTodos(DBConnect.START_DATE, DBConnect.END_DATE);
//		String[] from = new String[] { "task", "date" };
//		int[] to = new int[] { R.id.taskView, R.id.dateView };
//		ListView listView = (ListView) findViewById(R.id.listView1);
//		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.todo_text_view, c, from, to, 0);
//		listView.setAdapter(adapter);
		
		
//		Todo t4 = dbc.getTodo("Brief Android");
//		Log.v("DBOUTPUT Single", t4.toString());
//		Cursor c = dbc.getAllTodos(DBConnect.START_DATE, DBConnect.END_DATE); //"2013/04/13 15:00", //"2013/04/17 15:00");
//		c.moveToFirst();
//		while(!c.isAfterLast()){
//			Log.v("DBOUTPUT Cursor", c.getString(1) + " " + c.getString(2));
//			c.moveToNext();
//		}
	}

	public void gotoEntry(View view){
		Intent intent = new Intent(this, TodoEntryActivity.class);
    	startActivity(intent);
	}
	
	public void gotoList(View view){
		Intent intent = new Intent(this, TodoListActivity.class);
    	startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.action_new_todo:
	        	Intent intent = new Intent(this, TodoEntryActivity.class);
	        	startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
