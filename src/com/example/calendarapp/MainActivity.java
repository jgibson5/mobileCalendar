package com.example.calendarapp;

import com.example.calendarapp.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {
	
	public static final int NEW_TODO_REQUEST = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void gotoEntry(View view){
		startTodoEntry();
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
	        	startTodoEntry();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		switch (requestCode) {
			case NEW_TODO_REQUEST:
				if (resultCode == RESULT_OK) {
					DBConnect dbc = new DBConnect(this);
					Todo todo = (Todo) intent.getSerializableExtra("com.example.calendarapp.Todo");
					dbc.addTodo(todo);
				}
				break;
			default:
				break;
		}
	}
	
	private void startTodoEntry() {
    	Intent intent = new Intent(this, TodoEntryActivity.class);
    	startActivityForResult(intent, NEW_TODO_REQUEST);
	}

}
