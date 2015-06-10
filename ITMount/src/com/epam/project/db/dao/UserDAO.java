package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.model.Course;
import com.epam.project.db.model.User;
import com.epam.project.db.transformer.CourseTransformer;
import com.epam.project.db.transformer.UserTransformer;

public class UserDAO {

	public static final String SQL_UPDATE_USER = "UPDATE user SET name=?, midle_name=?, surname=?, birthday=?,"
			+ "role_id =?, password_hash=?, curriculum_vitae=?, description=?, is_active=?, is_confirmed=?, key1=?,"
			+ " image=?, email=? WHERE id=?";

	public static final String SQL_ADD_NEW_USER = "Insert into user (name,midle_name,surname,birthday,role_id,password_hash,"
			+ "curriculum_vitae,description, key1,image,email)"
			+ "value(?,?,?,?,?,?,?,?,?,?,?)";


	public static final String SQL_GET_ALL_USERS = "SELECT * FROM user";
	public static final String SQL_GET_USER = "SELECT * FROM user WHERE id=?";
	public static final String SQL_GET_USER_EMAIL = "SELECT * FROM user WHERE email=?";


	public static User getUser(Integer id, Connection connection) {

		ResultSet rs = null;
		User user = null;
		try {

			PreparedStatement st = connection.prepareStatement(SQL_GET_USER);
			st.setInt(1, id);
			rs = st.executeQuery();
			user = UserTransformer.getUser(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;

	}
	
	public static User getUserWhereEmail(String email, Connection connection) {

		ResultSet rs = null;
		User user = null;
		try {

			PreparedStatement st = connection.prepareStatement(SQL_GET_USER_EMAIL);
			st.setString(1, email);
			rs = st.executeQuery();
			user = UserTransformer.getUser(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;

	}

	public static List<User> getAllUsers(Connection connection) {
		ResultSet rs = null;
		List<User> list = null;
		try {

			PreparedStatement st = connection
					.prepareStatement(SQL_GET_ALL_USERS);
			rs = st.executeQuery();
			list = UserTransformer.getAllUsers(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void addNewUser(User user, Connection connection) {

		PreparedStatement stmt;

		Connection con = DBConnection.getConnection();

		try {
			stmt = con.prepareStatement(SQL_ADD_NEW_USER);

			stmt.setString(1, user.getName());
			stmt.setString(2, user.getMiddle_name());
			stmt.setString(3, user.getSurname());
			stmt.setDate(4, new Date(user.getBirtday().getTime()));
			stmt.setInt(5, user.getRole_id());
			stmt.setString(6, user.getPassword_hash());
			stmt.setString(7, user.getCurriculum_vitae());
			stmt.setString(8, user.getDescription());
			//stmt.setBoolean(9, user.getIs_active());
			//stmt.setBoolean(10, user.getIs_confirmed());
			stmt.setString(9, user.getKey());
			stmt.setString(10, user.getImage());
			stmt.setString(11, user.getEmail());

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	public static void updateUser(User user, Connection connection) {

		try {

			PreparedStatement stmt = connection
					.prepareStatement(SQL_UPDATE_USER);

			stmt.setString(1, user.getName());
			stmt.setString(2, user.getMiddle_name());
			stmt.setString(3, user.getSurname());
			stmt.setDate(4, new Date(user.getBirtday().getTime()));
			stmt.setInt(5, user.getRole_id());
			stmt.setString(6, user.getPassword_hash());
			stmt.setString(7, user.getCurriculum_vitae());
			stmt.setString(8, user.getDescription());
			stmt.setBoolean(9, user.getIs_active());
			stmt.setBoolean(10, user.getIs_confirmed());
			stmt.setString(11, user.getKey());
			stmt.setString(12, user.getImage());
			stmt.setString(13, user.getEmail());

			stmt.setInt(14, user.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
