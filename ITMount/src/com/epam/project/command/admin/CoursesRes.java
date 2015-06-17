package com.epam.project.command.admin;

import java.util.List;

import com.epam.project.db.model.Course;

public class CoursesRes {

	private List<Course> courses;
	private Integer amount;

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
