package com.example.calendarapp;

import java.util.ArrayList;
import java.util.List;

public class AvailableTimes {
	List<Boolean> times;
	DateTime start;
	DateTime end;
	
	public AvailableTimes(List<Event> events) {
		times = new ArrayList<Boolean>();
		start = new DateTime();
		end = start.clone();
		end.addHours(24);
		paint(events);
	}
	
	public void paint(Iterable<Event> events) {
		for (Event event: events) {
			paint(event);
		}
	}
	
	public void paint(Event event) {
		DateTime eventStart = event.getStart();
		DateTime eventEnd = event.getEnd();
		DateTime time = start.clone();
		
		for (int i = 0; i < times.size(); i++) {
			if (time.compareTo(eventEnd) > 0) break;
			if (time.compareTo(eventStart) > 0) times.set(i, false);
			time.addMinutes(15);
		}
	}
	
	public DateTime getFirstTime(int minutes) {
		int countNeeded = (minutes / 15) + 1;
		int count = 0;
		DateTime time = start.clone();
		DateTime blockStart = null;
		
		for (int i = 0; i < times.size(); i++) {
			if (times.get(i)) {
				if (blockStart == null) {
					blockStart = time.clone();
				}
				count++;
			}
			else {
				count = 0;
				blockStart = null;
			}
			if (count >= countNeeded) return blockStart;
			time.addMinutes(15);
		}
		return null;
	}
}
