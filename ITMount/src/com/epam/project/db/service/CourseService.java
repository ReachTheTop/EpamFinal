package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.CourseCorteg;
import com.epam.project.db.dao.CourseDAO;
import com.epam.project.db.model.Course;
import com.epam.project.db.transformer.CourseTransformer;

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

	public static List<Course> getAllActiveCourses() {
		Connection connection = DBConnection.getConnection();
		List<Course> list = CourseDAO.getAllActiveCourses(connection);
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
		closeConnection(connection);
		return course;
	}

	public static CourseCorteg getAllCourses(String searchToken, Integer page) {
		
		Connection connection = DBConnection.getConnection();
		CourseCorteg searchResult = CourseDAO.getAllCurses(connection,
				searchToken, page);
		searchResult.setCourses(CourseTransformer.getAllCourse(searchResult
				.getResultSet()));
		closeConnection(connection);
		return searchResult;
	}

	private static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
