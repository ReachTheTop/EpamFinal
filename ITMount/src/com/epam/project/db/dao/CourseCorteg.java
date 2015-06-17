package com.epam.project.db.dao;

import java.sql.ResultSet;
import java.util.List;

import com.epam.project.db.model.Course;

public class CourseCorteg{
	private ResultSet object1;
	private Integer object2;
	private List<Course> object3;

	public CourseCorteg() {

	}

	public void setResultSet(ResultSet object1) {
		this.object1 = object1;
	}

	public void setAmount(Integer object2) {
		this.object2 = object2;
	}

	public ResultSet getResultSet() {
		return object1;
	}

	public Integer getAmount() {
		return object2;
	}

	public List<Course> getCourses() {
		return object3;
	}

	public void setCourses(List<Course> object3) {
		this.object3 = object3;
	}

}
