package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import com.epam.project.db.model.Course;
import com.epam.project.db.transformer.CourseTransformer;

public class CourseDAO {

	private static final String INSERT = "INSERT INTO course(name,icon,description,status) VALUES(?,?,?,?);";
	private static final String UPDATE = "UPDATE course SET name=?, icon=?, description=?, status=?, date_exam=? WHERE id=?";
	private static final String DELETE = "DELETE FROM course WHERE id=?";
	private static final String SELECTALL = "SELECT * FROM course";
	private static final String SELECT = "SELECT * FROM course WHERE id=?";

	public static Integer addCourse(Course course, Connection connection) {
		PreparedStatement st = null;
		Integer course_id = null;
		try {
			st = connection.prepareStatement(INSERT,
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, course.getName());
			st.setString(2, course.getIcon());
			st.setString(3, course.getDescription());
			st.setString(4, course.getStatus());

			st.executeUpdate();
			ResultSet result = st.getGeneratedKeys();
			result.next();
			course_id = result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return course_id;
	}

	public static void delCourse(Integer id, Connection connection) {

		try {

			PreparedStatement st = connection.prepareStatement(DELETE);
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void updateCourse(Course course, Connection connection) {
		try {

			PreparedStatement st = connection.prepareStatement(UPDATE);

			st.setString(1, course.getName());
			st.setString(2, course.getIcon());
			st.setString(3, course.getDescription());
			st.setString(4, course.getStatus());
			st.setTimestamp(5, new Timestamp(course.getExam_date().getTime()));
			st.setInt(6, course.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Course> getAllCourse(Connection connection) {
		ResultSet rs = null;
		List<Course> list = null;
		try {

			PreparedStatement st = connection.prepareStatement(SELECTALL);
			rs = st.executeQuery();
			list = CourseTransformer.getAllCourse(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Course getCourse(Integer id, Connection connection) {
		ResultSet rs = null;
		Course course = null;
		try {

			PreparedStatement st = connection.prepareStatement(SELECT);
			st.setInt(1, id);
			rs = st.executeQuery();
			course = CourseTransformer.getCourse(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course;
	}

}
