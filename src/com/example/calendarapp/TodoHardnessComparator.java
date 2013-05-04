package com.example.calendarapp;

import java.util.Comparator;

public class TodoHardnessComparator implements Comparator<Todo>{

	public TodoHardnessComparator() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public int compare(Todo todo0, Todo todo1) {
		if (todo0.getDate().substring(0, 10).compareTo(todo1.getDate().substring(0, 10)) > 0) {
			return 1;
		} else if(todo0.getDate().substring(0, 10).compareTo(todo1.getDate().substring(0, 10)) < 0) {
			return -1;
		} else {
			return todo1.getHardness() - todo0.getHardness();
		}		
	}

}
