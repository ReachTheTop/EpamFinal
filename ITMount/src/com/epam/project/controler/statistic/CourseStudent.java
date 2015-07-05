package com.epam.project.controler.statistic;

public class CourseStudent {
	
	public String label;
	public int value;
	public CourseStudent(String label, int value) {
		super();
		this.label = label;
		this.value = value;
	}
	
	
	public CourseStudent() {
		super();
	}


	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}


	@Override
	public String toString() {
		return "CourseStudent [label=" + label + ", value=" + value + "]";
	}
	
	

}
