package com.example.calendarapp;

import java.util.Comparator;

/**
 * Class: TodoHardnessComparator
 * ------------------------------------------
 * This Comparator checks the "hardness" level of a todo and returns 1
 * if a todo is of a greater "hardness" than another. It prioritizes
 * the item that is harder to do.
 */

public class TodoHardnessComparator implements Comparator<Todo>{

	public TodoHardnessComparator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Function: compare
	 * -----------------------------------------------
	 * This function sorts first by due date, and then decreasing hardness
	 * within the due date. 
	 */
	@Override
	public int compare(Todo todo0, Todo todo1) {
		if (todo0.getDate().substring(0, 10).compareTo(
				todo1.getDate().substring(0, 10)) > 0) {
			return 1;
		} else if(todo0.getDate().substring(0, 10).compareTo(
				todo1.getDate().substring(0, 10)) < 0) {
			return -1;
		} else {
			return todo1.getHardness() - todo0.getHardness();
		}		
	}

}
