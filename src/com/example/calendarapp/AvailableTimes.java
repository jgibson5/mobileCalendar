package com.example.calendarapp;

public class AvailableTimes {
	BitList times;
	DateTime start;
	DateTime end;
	
	public AvailableTimes(Iterable<Placeable> events) {
		times = new BitList();
		start = new DateTime();
		end = start.clone();
		end.addHours(24);
		for (int i = 0; i < 96; i++) {
			times.add(true);
		}
		paint(events);
	}
	
	/**
	 * Place all start and end times from a list of events.
	 * @param events
	 */
	public void paint(Iterable<Placeable> events) {
		for (Placeable event: events) {
			paint(event);
		}
	}
	
	/**
	 * Place start and end time of a single event.
	 * @param event
	 */
	public void paint(Placeable event) {
		paint(event.getStart(), event.getEnd());
	}
	
	/**
	 * Add available times to BitList.
	 * @param paintStart
	 * @param paintEnd
	 */
	public void paint(DateTime paintStart, DateTime paintEnd) {
		DateTime time = start.clone();
		
		for (int i = 0; i < times.size(); i++) {
			if (time.compareTo(paintEnd) > 0) break;
			if (time.compareTo(paintStart) > 0) times.set(i, false);
			time.addMinutes(15);
		}
	}
	
	/**
	 * Take a todo and remove it's time from available times.
	 * Return a PlacedTodo
	 * @param todo
	 * @return
	 */
	public PlacedTodo placedTodo(Todo todo) {
		PlacedTodo placedTodo = new PlacedTodo(todo, 
				getFirstTime(todo.getTime_req()));
		paint(placedTodo.getStart(), placedTodo.getEnd());
		return placedTodo;
	}
	
	/**
	 * Get the first free time block for a given duration.
	 * @param minutes
	 * @return
	 */
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
	
}
