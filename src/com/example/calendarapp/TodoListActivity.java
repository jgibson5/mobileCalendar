package com.example.calendarapp;

import java.util.ArrayList;
import java.util.Collections;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.view.MenuItem;
import android.widget.ListView;

public class TodoListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo_list);
		DBConnect dbc = new DBConnect(this);
		//get the list of todos
		ArrayList<Todo> list = dbc.getAllTodos(DBConnect.START_DATE, DBConnect.END_DATE);
		//prioritize the todos
		Collections.sort(list, new TodoHardnessComparator());
		//get the list of events
		
		//create final list of todos and events
		ArrayList<Placeable> todoable = new ArrayList<Placeable>();
		
		//create AvailableTimes object with events
		
		// for each of the prioritized list of todos
		// toss it in AvailableTimes and insert todo with time into todoable
		// order that list, and return the names of the todos alone
		
		// doesn't include printing out event....
		
		TodoListView listView = (TodoListView) findViewById(R.id.todoListView);

		TodoListViewAdapter testAdapter = new TodoListViewAdapter(this, R.layout.todo_text_view, list);
		listView.setAdapter(testAdapter);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.todo_list, menu);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP, ActionBar.DISPLAY_HOME_AS_UP);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            setResult(RESULT_CANCELED, intent);
            finish();
            return true;
        default:
            return super.onOptionsItemSelected(item);

		}
	}

}
