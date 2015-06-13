package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.GroupUserDAO;
import com.epam.project.db.model.GroupUser;
import com.epam.project.db.model.User;

public class GroupUserService {

	public static GroupUser getById(Integer id) {

		Connection connection = DBConnection.getConnection();
		GroupUser groupUser = GroupUserDAO.getGroupUserById(id, connection);

		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groupUser;
	}

	public static List<User> getAllGroupUser(Integer group_id) {

		Connection connection = DBConnection.getConnection();
		List<User> list = GroupUserDAO.getAllGroupUser(connection, group_id);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
	
	public static List<User> getAllUserByGroupId(Integer groupeId) {

		Connection connection = DBConnection.getConnection();
		List<User> list = GroupUserDAO.getAllUserByGroupId(connection, groupeId);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
	
	public static User getTeacherByGroupId(Integer groupeId) {

		Connection connection = DBConnection.getConnection();
		User user = GroupUserDAO.getTeacherByGroupId(connection, groupeId);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;

	}
	

	public static void newGroupUser(GroupUser groupUser) {

		Connection connection = DBConnection.getConnection();
		GroupUserDAO.addNewGroupeUser(groupUser, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void updateGroupUser(GroupUser groupUser) {
		Connection connection = DBConnection.getConnection();
		GroupUserDAO.updateGroupUser(groupUser, connection);
		closeConnection(connection);
	}

	public static void deleteUser(Integer user_id, Integer group_id) {
		Connection connection = DBConnection.getConnection();
		GroupUserDAO.deleteUser(connection, user_id, group_id);
		closeConnection(connection);
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
