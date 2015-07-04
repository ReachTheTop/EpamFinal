package com.epam.project.controler.statistic;

public class CourseComplited {
	
	private String y;
	private int a;
	
	public CourseComplited() {
		super();
	}

	public CourseComplited(String year, int countStudent) {
		super();
		this.y = year;
		this.a = countStudent;
	}

	public String getYear() {
		return y;
	}

	public void setYear(String year) {
		this.y = year;
	}

	public int getCountStudent() {
		return a;
	}

	public void setCountStudent(int countStudent) {
		this.a = countStudent;
	}



}
