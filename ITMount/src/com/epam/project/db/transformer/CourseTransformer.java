package com.epam.project.db.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.project.controler.statistic.CourseComplited;
import com.epam.project.controler.statistic.CourseStudent;
import com.epam.project.db.model.Course;

public class CourseTransformer {

	public static List<CourseComplited> getCountCoursesStudentComplited(
			ResultSet rs) {

		List<CourseComplited> listCountStudent = new ArrayList<>();

		try {
			while (rs.next()) {				
				CourseComplited courseComplited = new CourseComplited();
				courseComplited.setCountStudent(rs.getInt(1));
				courseComplited.setYear(rs.getString(2));
				listCountStudent.add(courseComplited);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listCountStudent;
	}

	public static List<CourseStudent> getCountCoursesStudent(ResultSet rs) {

		List<CourseStudent> listUserCourses = new ArrayList<>();

		try {
			while (rs.next()) {
				CourseStudent cs = new CourseStudent();
				cs.setLabel(rs.getString(1));
				cs.setValue(rs.getInt(2));
				listUserCourses.add(cs);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listUserCourses;

	}

	public static Course getCourse(ResultSet rs) {

		Course course = null;

		try {
			while (rs.next()) {
				course = new Course();
				course.setId(rs.getInt(1));
				course.setName(rs.getString(2));
				course.setIcon(rs.getString(3));
				course.setDescription(rs.getString(4));
				course.setStatus(rs.getBoolean(5));

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
				course.setStatus(rs.getBoolean(5));

				list.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

}
