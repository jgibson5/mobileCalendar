package com.example.calendarapp;

import java.io.Serializable;
import java.text.ParseException;

public class Event implements Serializable {
	private static final long serialVersionUID = -5185389970304553876L;
	
	private DateTime start;
	private DateTime end;
	private String description;
	
	public Event(DateTime start, DateTime end, String description) {
		setStart(start);
		setEnd(end);
		setDescription(description);
	}
	
	public Event(String start, String end, String description) throws ParseException {
		this.start = new DateTime(start);
		this.end = new DateTime(end);
		setDescription(description);
	}
	
	public DateTime getStart() {
		return start;
	}
	
	public void setStart(DateTime start) {
		this.start = start.clone();
	}
	
	public DateTime getEnd() {
		return end;
	}
	
	public void setEnd(DateTime end) {
		this.end = end.clone();
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
