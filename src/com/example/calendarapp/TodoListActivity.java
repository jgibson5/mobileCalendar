package com.example.calendarapp;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TodoListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo_list);
		DBConnect dbc = new DBConnect(this);
		ArrayList<Todo> list = dbc.getAllTodos(DBConnect.START_DATE, DBConnect.END_DATE);

		ListView listView = (ListView) findViewById(R.id.listView1);

		ArrayList<String> stringList = new ArrayList<String>();
		for (Todo item : list) {
			stringList.add(item.toString());
		}
		ArrayAdapter<String> testAdapter = new ArrayAdapter<String>(this, R.layout.todo_text_view, R.id.taskView, stringList);
		listView.setAdapter(testAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.todo_list, menu);
		return true;
	}

}
