package ru.iteco.javacources.task2;

import java.io.Serializable;

public class Employee implements Serializable {

	private static final long serialVersionUID = 2L;

	private int number;
	
	private String name;
	
	private String jobTitle;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	@Override
	public String toString(){
		return number + ", " + name + ", " + jobTitle;
	}
		
}
