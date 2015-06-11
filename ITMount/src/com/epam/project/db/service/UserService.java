package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.CourseDAO;
import com.epam.project.db.dao.UserDAO;
import com.epam.project.db.model.Course;
import com.epam.project.db.model.User;

public class UserService {

	public static List<User> getByRole(String role) {
		Connection connection = DBConnection.getConnection();
		List<User> user = UserDAO.getByRole(role, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public static User getUser(Integer id) {

		Connection connection = DBConnection.getConnection();
		User user = UserDAO.getUser(id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public static User getUserWhereEmail(String email) {

		Connection connection = DBConnection.getConnection();
		User user = UserDAO.getUserWhereEmail(email, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;

	}

	public static List<User> getAllUsers() {
		Connection connection = DBConnection.getConnection();
		List<User> list = UserDAO.getAllUsers(connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static void updateUser(User user) {

		Connection connection = DBConnection.getConnection();

		UserDAO.updateUser(user, connection);

		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void addNewUser(User user) {

		Connection connection = DBConnection.getConnection();
		UserDAO.addNewUser(user, connection);

		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}