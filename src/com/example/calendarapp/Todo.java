package com.example.calendarapp;

import java.io.Serializable;

/**
 * Class: Todo
 * -------------------------------------
 * A Todo stores all the information for a new task the user needs
 * to complete. It has fields for the name, due date, hardness, and
 * time required.
 */

public class Todo implements Comparable<Todo>, Serializable {

	private static final long serialVersionUID = -362836764856610952L;
	private String todo;
	private String date;
	private String original;
	private int hardness;
	private int time_req;


	/**
	 * Function: Todo
	 * ------------------------------------
	 * This constructor is for when the name of the activity and the
	 * due date is passed in as one String.
	 * 
	 * @param info
	 */
	
	public Todo(String info){
		this.original = info;
		String[] stuff = info.split("\\s*,\\s*");
		this.todo = stuff[0];
		this.date = stuff[1];
	}

	/**
	 * Function: Todo
	 * ------------------------------------
	 * This constructor is for when the name of the activity and the
	 * due date are passed in separately as strings.
	 * 
	 * @param todo
	 * @param date
	 */

	public Todo(String todo, String date){
		this.original = todo + ", "+ date;
		this.todo = todo;
		this.date = date;
	}

	/**
	 * Function: todo
	 * ---------------------------------------------
	 * Constructor for an upgraded GUI.
	 * 
	 * @param todo
	 * @param date
	 * @param hardness
	 * @param time_req
	 */
	public Todo(String todo, String date, int hardness, int time_req) {
		this.original = todo + ", "+ date;
		this.todo = todo;
		this.date = date;
		this.setHardness(hardness);
		this.setTime_req(time_req);
	}
	

	/**
	 * Function: getOriginal
	 * -----------------------------------------------
	 * This function is used to retrieve the original string
	 * of user input for the constructor called when the string and due date
	 * were mashed together as one.
	 * 
	 * @return
	 */
	public String getOriginal() {
		return original;
	}

	/**
	 * Function: setOriginal
	 * -------------------------------------------
	 * This function is useful for testing purposes when the todo name and the
	 * due date need to be processed as one combined String.
	 * @param original
	 */
	public void setOriginal(String original) {
		this.original = original;
	}

	/**
	 * Function: toString
	 * -------------------------------------------
	 * This function returns the original string when combined together
	 * and the hardness.
	 */
	
	public String toString(){
		return this.original + "   " + this.hardness;
	}
	
	
	/**
	 * Function: compareTo
	 * -----------------------------------------
	 * This function enables the comparator to work.
	 * 
	 * @param todo
	 */
	public int compareTo(Todo t){
		return this.date.compareTo(t.getDate());
	}

	/**
	 * Function: getTodo
	 * -----------------------------------------
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

