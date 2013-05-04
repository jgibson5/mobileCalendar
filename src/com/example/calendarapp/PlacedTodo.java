package com.example.calendarapp;

/**
 * Class for wrapping the Todo when it has been placed in a time slot.
 * @author hari
 *
 */
public class PlacedTodo extends Placeable{

	protected Todo todo;
	/**
	 * Constructor for PlacedTodo. Takes in a Todo todo and DateTime start.
	 * @param todo
	 * @param start
	 */
	public PlacedTodo(Todo todo, DateTime start) {
		this.todo = todo;
		this.start = start;
		this.end = this.start.clone();
		this.end.addMinutes(this.todo.getTime_req());
		this.description = this.todo.getTodo(); 
	}

	/**
	 * Callback for the ListView to execute when the PlacedTodo is tapped.
	 * 
	 * NOT IMPLEMENTED
	 */
	@Override
	void doEdit() {
		// what happens when you choose to edit it
		// it's editable b/c it's a todo, unlike an event
		
	}

	@Override
	public int getHardness() {
		return todo.getHardness();
	}

}
