package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.CourseDAO;
import com.epam.project.db.model.Course;

public class CourseService {

	public static void trigerCourse(Course course) {
		Connection connection = DBConnection.getConnection();
		CourseDAO.trigerCourse(course, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Integer addCourse(Course course) {
		Connection connection = DBConnection.getConnection();
		Integer course_id = CourseDAO.addCourse(course, connection);

		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course_id;

	}

	public static void delCourse(Integer id) {
		Connection connection = DBConnection.getConnection();
		CourseDAO.delCourse(id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateCourse(Course course) {
		Connection connection = DBConnection.getConnection();
		CourseDAO.updateCourse(course, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Course> getAllCourses() {
		Connection connection = DBConnection.getConnection();
		List<Course> list = CourseDAO.getAllCourse(connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static Course getCourse(Integer id) {
		Connection connection = DBConnection.getConnection();
		Course course = CourseDAO.getCourse(id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course;
	}
}
