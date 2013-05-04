package com.example.calendarapp;

/**
 * Abstract class representing a todo or event that can be placed in a ListView
 * Extended by Event and PlacedTodo
 * @author hari
 *
 */
public abstract class Placeable implements Comparable<Placeable> {

	protected String description;
	protected DateTime start;
	protected DateTime end;
	
	public Placeable() {}
	
	/** 
	 * enables editing callback based off of child class
	 */
	abstract void doEdit();
	
	public DateTime getStart() {
		return this.start;
	}
	
	public DateTime getEnd() {
		return this.end;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Get duration in minutes
	 * @return long
	 */
	public long getDuration() {
		return Math.round((end.getMillis() - start.getMillis()) / (60 * 1000));
	}
	
	/**
	 * Child class must override because hardness only makes sense for Todos.
	 * Events should return 0
	 */
	public abstract int getHardness();
	
	/**
	 * Compare the start times of two Placeables
	 */
	public int compareTo(Placeable another) {
		return start.compareTo(another.getStart());
	}

}
