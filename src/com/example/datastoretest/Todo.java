package com.example.datastoretest;


public class Todo implements Comparable<Todo>{
	private String todo;
	private String date;
	private String original;
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
		return this.original;
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

}

