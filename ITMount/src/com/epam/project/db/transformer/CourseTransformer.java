package com.epam.project.db.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.project.db.model.Course;


public class CourseTransformer {

	public static Course getCourse(ResultSet rs) {

		Course course = null;

		try {
			while (rs.next()) {
				course = new Course();
				course.setId(rs.getInt(1));
				course.setName(rs.getString(2));
				course.setIcon(rs.getString(3));
				course.setDescription(rs.getString(4));
				course.setStatus(rs.getString(5));
				

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course;

	}

	public static List<Course> getAllCourse(ResultSet rs) {
		List<Course> list = new ArrayList<Course>();
		Course course = null;

		try {
			while (rs.next()) {
				course = new Course();
				course.setId(rs.getInt(1));
				course.setName(rs.getString(2));
				course.setIcon(rs.getString(3));
				course.setDescription(rs.getString(4));
				course.setStatus(rs.getString(5));
				
				list.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
}
