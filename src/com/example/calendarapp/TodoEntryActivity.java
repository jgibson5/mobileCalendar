package com.example.calendarapp;

import java.util.ArrayList;

import com.example.calendarapp.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;



/**
 * Class: TodoEntryActivity
 * ---------------------------------------------
 * This activity is the interface where new todos are entered.
 * (JAVADOCS)
 */

public class TodoEntryActivity extends Activity {

	// In order for the next activity to query the extra data, 
	//   you should define the key for your intent's extra using a public constant
    public final static String EXTRA_MESSAGE = "com.example.calendarapp.MESSAGE";
    private ArrayList<String> durationsTextList;
    private Integer[] durationsValueList = {5, 10, 15, 30, 45, 60, 90, 120, 240, 480, 1000};
    
    
    /**
     * Function: onCreate
     * --------------------------------------
     * This function enables the user to specify the new todo item, with features
     * for adding the name, due date, and seek bars for "hardness" and time required.
     * 
     * (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo_entry);
		getStringResources();
		SeekBar durationBar = (SeekBar) findViewById(R.id.durationBar);
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
	
	
	/**
	 * Function: getStringResources
	 * ---------------------------------------------
	 * This function sends the seek bar a duration value
	 * so it displays how the time required according to the
	 * strings.xml file.
	 */
	
	private void getStringResources(){
		durationsTextList = new ArrayList<String>(){
			private static final long serialVersionUID = 1L;
		{
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

	
	/**
	 * Function: onCreateOptionsMenu
	 * ---------------------------------------------------
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	
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
	
	
	/**
	 * Function: saveTodo
	 * -------------------------------------------------
	 * This function is called when the user clicks the Save button.
	 * It sends the inputted information via an intent to MainActivity.
	 * @param view
	 */
	
	public void saveTodo(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		EditText todoText = (EditText) findViewById(R.id.todoName);
		EditText dateText = (EditText) findViewById(R.id.dueDate);
		SeekBar priBar = (SeekBar) findViewById(R.id.priorityBar);
		SeekBar durBar = (SeekBar) findViewById(R.id.durationBar);
		int priority = priBar.getProgress();
		int duration = durBar.getProgress();
		String todoStr = todoText.getText().toString();
		String dateStr = dateText.getText().toString();
		Todo todo = new Todo (todoStr, dateStr, priority, durationsValueList[duration]);
		intent.putExtra("com.example.calendarapp.Todo", todo);
		setResult(RESULT_OK, intent);
		finish();
	}
	
	/**
	 * Function: cancel
	 * ------------------------------------------
	 * This function returns to the home screen without saving.
	 * @param view
	 */
	
	public void cancel(View view) {
		setResult(RESULT_CANCELED);
		finish();
	}

}
