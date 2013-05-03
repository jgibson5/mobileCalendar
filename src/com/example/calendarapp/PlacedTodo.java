/**
 * 
 */
package com.example.calendarapp;

/**
 * @author hari
 *
 */
public class PlacedTodo extends Placeable{

	protected Todo todo;
	/**
	 * 
	 */
	public PlacedTodo(Todo todo, DateTime start) {
		this.todo = todo;
		this.start = start;
		this.end = this.start.clone();
		this.end.addMinutes(this.todo.getTime_req());
		this.description = this.todo.getTodo(); 
	}

	@Override
	void doEdit() {
		// what happens when you choose to edit it
		// it's editable b/c it's a todo, unlike an event
		
	}

	@Override
	public int getHardness() {
		// TODO Auto-generated method stub
		return todo.getHardness();
	}

}
