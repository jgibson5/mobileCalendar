package com.example.calendarapp;

import java.util.ArrayList;

import com.example.calendarapp.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class TodoEntryActivity extends Activity {

	// In order for the next activity to query the extra data, 
	//   you should define the key for your intent's extra using a public constant
    public final static String EXTRA_MESSAGE = "com.example.calendarapp.MESSAGE";
    private ArrayList<String> durationsTextList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo_entry);
		getStringResources();
		SeekBar durationBar = (SeekBar) findViewById(R.id.durationBar);
		String s = this.getResources().getString(R.string.duration_value0);
		durationBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
	        @Override
	        public void onStopTrackingTouch(SeekBar seekBar) {
	        }
	        @Override
	        public void onStartTrackingTouch(SeekBar seekBar) {
	        }
	        @Override
	        public void onProgressChanged(SeekBar seekBar, int progress,
	                boolean fromUser) {
	        	TextView durationText = (TextView) findViewById(R.id.durationText);
	    		durationText.setText(durationsTextList.get(progress));
	        }
	   });
	}
	
	private void getStringResources(){
		durationsTextList = new ArrayList<String>(){{
	    	add(getString(R.string.duration_value0));
	    	add(getString(R.string.duration_value1));
	    	add(getString(R.string.duration_value2));
	    	add(getString(R.string.duration_value3));
	    	add(getString(R.string.duration_value4));
	    	add(getString(R.string.duration_value5));
	    	add(getString(R.string.duration_value6));
	    	add(getString(R.string.duration_value7));
	    	add(getString(R.string.duration_value8));
	    	add(getString(R.string.duration_value9));
	    	add(getString(R.string.duration_value10));
	    }};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
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
	
	/** Called when the user clicks the Save button */
	public void saveTodo(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		EditText todoText = (EditText) findViewById(R.id.todoName);
		EditText dateText = (EditText) findViewById(R.id.dueDate);
		SeekBar priBar = (SeekBar) findViewById(R.id.priorityBar);
		int priority = priBar.getProgress();
		String todoStr = todoText.getText().toString();
		String dateStr = dateText.getText().toString();
		Todo todo = new Todo (todoStr, dateStr, priority, 0);
		intent.putExtra("com.example.calendarapp.Todo", todo);
		setResult(RESULT_OK, intent);
		finish();
	}
	
	public void cancel(View view) {
		setResult(RESULT_CANCELED);
		finish();
	}


}
