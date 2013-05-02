package com.example.calendarapp;

import java.io.Serializable;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Placeable implements Serializable{
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
	
	public Event(Long start, Long end, String description){
		this.start = new DateTime(start);
		this.end = new DateTime(end);
		setDescription(description);
	}
	
	public Event(Long start, String duration, String description){
		this.start = new DateTime(start);
		this.end = new DateTime(endFromDuration(start, duration));
		setDescription(description);
	}
	
	public Long endFromDuration(Long start, String duration){
		Long nd = start;
		Pattern pattern = Pattern.compile("([0-9]*[D|H|M|S])");
		Matcher matcher = pattern.matcher(duration);
		while (matcher.find()) {
            String group = matcher.group();
            if (group.charAt(group.length()-1) == 'D'){
            	String numbers = group.substring(0, group.length()-1);
            	int millis = Integer.parseInt(numbers);
            	millis = millis * 86400000;
            	nd = nd + millis;
            }else if (group.charAt(group.length()-1) == 'H'){
            	String numbers = group.substring(0, group.length()-1);
            	int millis = Integer.parseInt(numbers);
            	millis = millis * 3600000;
            	nd = nd + millis;
            }else if (group.charAt(group.length()-1) == 'M'){
            	String numbers = group.substring(0, group.length()-1);
            	int millis = Integer.parseInt(numbers);
            	millis = millis * 60000;
            	nd = nd + millis;
            }else if (group.charAt(group.length()-1) == 'S'){
            	String numbers = group.substring(0, group.length()-1);
            	int millis = Integer.parseInt(numbers);
            	millis = millis * 1000;
            	nd = nd + millis;
            }
        }
		return nd;
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

	@Override
	void doEdit() {
		// do nothing b/c it's an event
		
	}
}
