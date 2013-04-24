package com.example.datastoretest;

import java.io.Serializable;


public class Todo implements Comparable<Todo>, Serializable {

	private static final long serialVersionUID = -362836764856610952L;
	private String todo;
	private String date;
	private String original;
	private int hardness;
	private int time_req;
	/**
	 * @param args
	 */

	public Todo(String info){
		this.original = info;
		String[] stuff = info.split("\\s*,\\s*");
		this.todo = stuff[0];
		this.date = stuff[1];
	}
	
	public Todo(String todo, String date){
		this.original = todo + ", "+ date;
		this.todo = todo;
		this.date = date;
	}
	
	public Todo(String todo, String date, int hardness, int time_req) {
		this.original = todo + ", "+ date;
		this.todo = todo;
		this.date = date;
		this.setHardness(hardness);
		this.setTime_req(time_req);
	}
	
	/**
	 * @return the original
	 */
	public String getOriginal() {
		return original;
	}

	/**
	 * @param original the original to set
	 */
	public void setOriginal(String original) {
		this.original = original;
	}

	public String toString(){
		return this.original + "   " + this.hardness;
	}
	
	public int compareTo(Todo t){
		return this.date.compareTo(t.getDate());
	}

	/**
	 * @return the todo
	 */
	public String getTodo() {
		return todo;
	}

	/**
	 * @param todo the todo to set
	 */
	public void setTodo(String todo) {
		this.todo = todo;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the hardness
	 */
	public int getHardness() {
		return hardness;
	}

	/**
	 * @param hardness the hardness to set
	 */
	public void setHardness(int hardness) {
		this.hardness = hardness;
	}

	/**
	 * @return the time_req
	 */
	public int getTime_req() {
		return time_req;
	}

	/**
	 * @param time_req the time_req to set
	 */
	public void setTime_req(int time_req) {
		this.time_req = time_req;
	}

}

