/**
 * 
 */
package com.example.calendarapp;

/**
 * @author hari
 *
 */
public abstract class Placeable implements Comparable<Placeable> {

	protected String description;
	protected DateTime start;
	protected DateTime end;
	
	public Placeable() {
		// TODO Auto-generated constructor stub
	}
	
	// enables editing if it's a todo, and not if it's an event
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
	
	public long getDuration() {
		return Math.round((end.getMillis() - start.getMillis()) / (60 * 1000));
	}
	
	public abstract int getHardness();
	
	public int compareTo(Placeable another) {
		return start.compareTo(another.getStart());
	}

}
