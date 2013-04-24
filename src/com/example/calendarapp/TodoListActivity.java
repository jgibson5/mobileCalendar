package com.example.calendarapp;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.widget.ListView;

public class TodoListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo_list);
		
		// Connect to database and list all activities.
		DBConnect dbc = new DBConnect(this);
		Cursor c = dbc.getAllTodos(DBConnect.START_DATE, DBConnect.END_DATE);
		String[] from = new String[] { "task", "date" };
		int[] to = new int[] { R.id.taskView, R.id.dateView };
		ListView listView = (ListView) findViewById(R.id.listView1);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.todo_text_view, c, from, to, 0);
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.todo_list, menu);
		return true;
	}

}
