package com.example.calendarapp;

public class AvailableTimes {
	BitList times;
	DateTime start;
	DateTime end;
	
	public AvailableTimes(Iterable<Event> events) {
		times = new BitList();
		start = new DateTime();
		end = start.clone();
		end.addHours(24);
		for (int i = 0; i < 96; i++) {
			times.add(true);
		}
		paint(events);
	}
	
	public void paint(Iterable<Event> events) {
		for (Event event: events) {
			paint(event);
		}
	}
	
	public void paint(Event event) {
		paint(event.getStart(), event.getEnd());
	}
	
	public void paint(DateTime paintStart, DateTime paintEnd) {
		DateTime time = start.clone();
		
		for (int i = 0; i < times.size(); i++) {
			if (time.compareTo(paintEnd) > 0) break;
			if (time.compareTo(paintStart) > 0) times.set(i, false);
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
			if (count >= countNeeded) {
				paint (blockStart, time);
				return blockStart;
			}
			time.addMinutes(15);
		}
		return null;
	}
	
	public void testGetFirstTime(int duration) {
		System.out.println(getFirstTime(30));
	}
	
}
