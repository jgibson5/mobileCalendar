package com.example.calendarapp;

import java.util.ArrayList;
import java.util.Collections;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class TodoListActivity extends Activity {


	/**
	 * Function: onCreate
	 * ----------------------------------------
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 * 
	 * Retrieves list of todos and events, orders todos and events
	 * based off available times between events and priority
	 * of todos. It then sends these "Placeable" objects to the
	 * PlaceableListViewAdapter.
	 * 
	 */
	
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
		//ArrayList<Placeable> placeable = new ArrayList<Placeable>();
		ArrayList<Placeable> events = EventGetter.getEvents(this);
		//create AvailableTimes object with events
		
		AvailableTimes availableTimes = new AvailableTimes(events);
		ArrayList<Placeable> placeables = new ArrayList<Placeable>(events);
		
		// for each of the prioritized list of todos
		// toss it in AvailableTimes and insert todo with time into todoable
		// order that list, and return the names of the todos alone
		
		// doesn't include printing out event....
		
		for (Todo todo : list) {
			placeables.add(availableTimes.placedTodo(todo));
		}
		
		Collections.sort(placeables);
		
		PlaceableListView listView = (PlaceableListView) findViewById(R.id.todoListView);

		PlaceableListViewAdapter testAdapter = new PlaceableListViewAdapter(this, R.layout.placeable_list_text_view, placeables);
		listView.setAdapter(testAdapter);
	}

	/**
	 * Function: onCreateOptionsMenu
	 * ---------------------------------------------------
	 * Menu Inflator "inflates the menu"; this adds items to the action bar if it is present.
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 * 
	 */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
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
