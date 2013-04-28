package com.example.calendarapp;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTime implements Serializable, Cloneable, Comparable<DateTime> {
	private static final long serialVersionUID = -48980714199406187L;
	
	private long millis = 0;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.US);
	
	public DateTime() {
		Date date = new Date();
		millis = date.getTime();
	}
	
	public DateTime(long millis) {
		this.millis = millis;
	}
	
	public DateTime(String date) throws ParseException {
		this.parse(date);
	}
	
	public void parse(String date) throws ParseException {
		millis = dateFormat.parse(date).getTime();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj.getClass() == this.getClass()) return this.compareTo((DateTime) obj) == 0;
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

}
