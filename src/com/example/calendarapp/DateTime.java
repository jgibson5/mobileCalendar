package com.example.calendarapp;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A specific point in time.
 * 
 * Includes methods for interoperating with out String date format.
 * 
 * @author rbutler
 *
 */
public class DateTime 
		implements Serializable, Cloneable, Comparable<DateTime> {
	
	private static final long serialVersionUID = -48980714199406187L;
	
	private long millis = 0;
	private static SimpleDateFormat dateFormat = 
			new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.US);
	
	/**
	 * Create a new DateTime object for right now.
	 */
	public DateTime() {
		Date date = new Date();
		millis = date.getTime();
	}
	
	/**
	 * Create a new DateTime for a specific time.
	 * @param millis
	 */
	public DateTime(long millis) {
		this.millis = millis;
	}
	
	/**
	 * Create new DateTime from string version of a date.
	 * @param date
	 * @throws ParseException
	 */
	public DateTime(String date) throws ParseException {
		this.parse(date);
	}
	
	/**
	 * Parse a string date into milliseconds.
	 * @param date
	 * @throws ParseException
	 */
	public void parse(String date) throws ParseException {
		millis = dateFormat.parse(date).getTime();
	}
	
	/**
	 * Add time in milliseconds to millis.
	 * @param milliseconds
	 */
	public void addMilliseconds(long milliseconds) {
		this.millis += milliseconds;
	}
	
	/**
	 * Add time in seconds to millis.
	 * @param seconds
	 */
	public void addSeconds(long seconds) {
		this.millis += seconds * 1000;
	}

	/**
	 * Add time in minutes to millis.
	 * @param minutes
	 */
	public void addMinutes(long minutes) {
		this.millis += minutes * 60000;
	}

	/**
	 * Add time in hours to millis.
	 * @param hours
	 */
	public void addHours(long hours) {
		this.millis += hours * 3600000;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj.getClass() == this.getClass()) {
			return this.compareTo((DateTime) obj) == 0;
		}
		return false;
	}

	@Override
	public int compareTo(DateTime another) {
		return (int) Math.signum(this.millis - another.millis);
	}
	
	@Override
	public DateTime clone() {
		return new DateTime(millis);
	}
	
	@Override
	public String toString() {
		Date date = new Date(millis);
		return dateFormat.format(date);
	}
	
	/**
	 * @return millis
	 */
	public long getMillis() {
		return this.millis;
	}

}
