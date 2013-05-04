package com.example.calendarapp;

import com.example.calendarapp.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Main activity for Calendar app
 * @author rbutler
 *
 */
public class MainActivity extends Activity {
	
	public static final int NEW_TODO_REQUEST = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
	}
	
	/**
	 * Starts the new Todo activity.
	 * @param view
	 */
	public void gotoEntry(View view){
		startTodoEntry();
	}
	
	/**
	 * Create an Intent and launch TodoListActivity. 
	 * @param view
	 */
	public void gotoList(View view){
		Intent intent = new Intent(this, TodoListActivity.class);
    	startActivity(intent);
	}
	
	/**
	 * Inflate the menu; this adds items to the action bar if it is present.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * General callback for menu.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.action_new_todo:
	        	startTodoEntry();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	/**
	 * If the requestCode is for a new Todo, gets the Todo from the
	 * intent and stores it in the database.
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, 
			Intent intent) {
		switch (requestCode) {
			case NEW_TODO_REQUEST:
				if (resultCode == RESULT_OK) {
					DBConnect dbc = new DBConnect(this);
					Todo todo = (Todo) intent.getSerializableExtra(
							"com.example.calendarapp.Todo");
					dbc.addTodo(todo);
				}
				break;
			default:
				break;
		}
	}
	
	/**
	 * Create an intent and launch TodoEntryActivity.
	 */
	private void startTodoEntry() {
    	Intent intent = new Intent(this, TodoEntryActivity.class);
    	startActivityForResult(intent, NEW_TODO_REQUEST);
	}

}
