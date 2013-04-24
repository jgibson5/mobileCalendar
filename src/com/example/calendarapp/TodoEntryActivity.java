package com.example.calendarapp;

import com.example.calendarapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class TodoEntryActivity extends Activity {

	// In order for the next activity to query the extra data, 
	//   you should define the key for your intent's extra using a public constant
    public final static String EXTRA_MESSAGE = "com.example.calendarapp.MESSAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo_entry);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/** Called when the user clicks the Save button */
	public void saveTodo(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		EditText todoText = (EditText) findViewById(R.id.todoName);
		EditText dateText = (EditText) findViewById(R.id.dueDate);
		String todoStr = todoText.getText().toString();
		String dateStr = dateText.getText().toString();
		Todo todo = new Todo (dateStr, todoStr);
		intent.putExtra("com.example.calendarapp.Todo", todo);
		setResult(RESULT_OK, intent);
		finish();
	}


}
