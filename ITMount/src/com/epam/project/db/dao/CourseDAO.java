package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.epam.project.db.model.Course;
import com.epam.project.db.transformer.CourseTransformer;

public class CourseDAO {

	private static final String INSERT = "INSERT INTO course(name,icon,description) VALUES(?,?,?);";
	private static final String BASE_GROUP = "INSERT INTO group1(course_id, name, is_active) VALUE (?,?,?);";

	private static final String UPDATE = "UPDATE course SET name=?, icon=?, description=?, is_active=?, date_exam=? WHERE id=?";

	private static final String GET_ALL = "SELECT * FROM course;";

	private static final String SELECTALL = "SELECT * FROM course WHERE is_active =1;";

	private static final String SELECT = "SELECT * FROM course WHERE id=?";

	private static final String TRIGER = "UPDATE course set is_active = !is_active  WHERE id = ?";

	public static void trigerCourse(Course course, Connection connection) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(TRIGER);

			st.setInt(1, course.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Integer addCourse(Course course, Connection connection) {
		PreparedStatement st = null;
		Integer course_id = null;
		try {
			connection.setAutoCommit(false);
			st = connection.prepareStatement(INSERT,
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, course.getName());
			st.setString(2, course.getIcon());
			st.setString(3, course.getDescription());

			st.executeUpdate();

			ResultSet resultSet = st.getGeneratedKeys();
			resultSet.next();
			Integer id = resultSet.getInt(1);
			st = connection.prepareStatement(BASE_GROUP);

			st.setInt(1, id);
			st.setString(2, "Base");
			st.setBoolean(3, true);

			st.executeUpdate();

			ResultSet result = st.getGeneratedKeys();
			result.next();
			course_id = result.getInt(1);

			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return course_id;
	}

	public static void updateCourse(Course course, Connection connection) {
		try {

			PreparedStatement st = connection.prepareStatement(UPDATE);

			st.setString(1, course.getName());
			st.setString(2, course.getIcon());
			st.setString(3, course.getDescription());

			st.setInt(4, course.getId());
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
