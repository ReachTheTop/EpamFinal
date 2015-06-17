package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.UserDAO;
import com.epam.project.db.model.User;
import com.epam.project.db.transformer.UserTransformer;

public class UserService {

	public static List<User> getByRole(String role) {
		Connection connection = DBConnection.getConnection();
		List<User> users = UserDAO.getByRole(role, connection);
		for (User user : users) {
			user.setRole(UserDAO.getRole(connection, user.getRole_id()));
		}
		closeConnection(connection);
		return users;
	}

	public static User getUser(Integer id) {

		Connection connection = DBConnection.getConnection();
		User user = UserDAO.getUser(id, connection);
		user.setRole(UserDAO.getRole(connection, user.getRole_id()));
		closeConnection(connection);
		return user;
	}

	public static User getUserWhereEmail(String email) {

		Connection connection = DBConnection.getConnection();
		User user = UserDAO.getUserWhereEmail(email, connection);
		user.setRole(UserDAO.getRole(connection, user.getRole_id()));

		closeConnection(connection);
		return user;

	}

	public static List<User> getAllUsers() {
		Connection connection = DBConnection.getConnection();
		List<User> list = UserDAO.getAllUsers(connection);
		for (User user : list) {
			user.setRole(UserDAO.getRole(connection, user.getRole_id()));
		}
		closeConnection(connection);
		return list;
	}

	public static void updateUser(User user) {

		Connection connection = DBConnection.getConnection();

		UserDAO.updateUser(user, connection);

		closeConnection(connection);

	}

	public static void addNewUser(User user) {

		Connection connection = DBConnection.getConnection();
		UserDAO.addNewUser(user, connection);

		closeConnection(connection);

	}

	public static List<User> getUserByToken(String token) {
		List<User> users = null;
		Connection connection = DBConnection.getConnection();
		users = UserTransformer.getAllUsers(UserDAO.getUsersByToken(connection,
				token));
		for (User user : users) {
			user.setRole(UserDAO.getRole(connection, user.getRole_id()));
		}

		closeConnection(connection);
		return users;
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