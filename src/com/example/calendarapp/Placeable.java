/**
 * 
 */
package com.example.calendarapp;

/**
 * @author hari
 *
 */
public abstract class Placeable {

	protected String description;
	protected DateTime start;
	protected DateTime end;
	
	public Placeable() {
		// TODO Auto-generated constructor stub
	}
	
	// enables editing if it's a todo, and not if it's an event
	abstract void doEdit();

}
