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
	
	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {
	    // Do something in response to button
		//Intent intent = new Intent(this, DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.todoName);
		EditText editText2 = (EditText) findViewById(R.id.dueDate);
		String todoName = editText.getText().toString();
		String dueDate = editText2.getText().toString();
		String message = todoName+dueDate;
		//intent.putExtra(EXTRA_MESSAGE, message);
		// To start an activity, call startActivity() and pass it your Intent.
		//   The system receives this call and starts an instance of the Activity specified by the Intent.
		//startActivity(intent);
	}


}
