package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.GroupExamDAO;
import com.epam.project.db.dao.GroupUserDAO;
import com.epam.project.db.model.GroupExamModel;
import com.epam.project.db.model.GroupUser;
import com.epam.project.db.model.User;

public class GroupUserService {

	public static GroupUser getById(Integer id) {

		Connection connection = DBConnection.getConnection();
		GroupUser groupUser = GroupUserDAO.getGroupUserById(id, connection);

		closeConnection(connection);
		return groupUser;
	}

	public static List<User> getAllGroupUser(Integer group_id) {

		Connection connection = DBConnection.getConnection();
		List<User> list = GroupUserDAO.getAllGroupUser(connection, group_id);
		closeConnection(connection);
		return list;

	}

	public static List<User> getAllUserByGroupId(Integer groupeId) {

		Connection connection = DBConnection.getConnection();
		List<User> list = GroupUserDAO
				.getAllUserByGroupId(connection, groupeId);
		closeConnection(connection);
		return list;

	}

	public static User getTeacherByGroupId(Integer groupeId) {

		Connection connection = DBConnection.getConnection();
		User user = GroupUserDAO.getTeacherByGroupId(connection, groupeId);
		closeConnection(connection);
		return user;

	}

	public static void newGroupUser(GroupUser groupUser) {

		Connection connection = DBConnection.getConnection();
		GroupUserDAO.addNewGroupeUser(groupUser, connection);

		closeConnection(connection);

	}

	public static void updateGroupUser(GroupUser groupUser) {
		Connection connection = DBConnection.getConnection();
		GroupUserDAO.updateGroupUser(groupUser, connection);
		closeConnection(connection);
	}

	public static void deleteUsersFromGroup(List<String> users, Integer group_id) {
		Connection connection = DBConnection.getConnection();
		GroupUserDAO.deleteUsersFromGroup(connection, group_id, users);
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

	public static void addUsersToGroup(Integer group_id, List<String> users) {
		Connection connection = DBConnection.getConnection();
		GroupUserDAO.addUsersToGroup(connection, group_id, users);
		closeConnection(connection);
	}

	public static void leaveUsersInGroup(Integer group_id, List<String> users) {
		Connection connection = DBConnection.getConnection();
		GroupUserDAO.leaveUsersInGroup(connection, group_id, users);
		closeConnection(connection);
	}

	public static void rebaseUsers(Integer old_group_id, Integer new_group_id,
			List<String> users) {
		Connection connection = DBConnection.getConnection();
		GroupUserDAO.rebaseUsers(connection, old_group_id, new_group_id, users);
		closeConnection(connection);
	}

	public static GroupUser getByGroupAndUserId(Integer group_id,
			Integer user_id) {
		Connection connection = DBConnection.getConnection();
		GroupUser association = null;
		association = GroupUserDAO.getByGroupAndUserId(connection, group_id,
				user_id);

		GroupExamModel exam = null;
		if (association != null) {
			exam = GroupExamDAO.getById(connection, association.getExam_id());
		}
		if (exam != null) {
			Date date = exam.getExam_date();
			association.setExam(exam);
		}

		closeConnection(connection);
		return association;
	}

	public static void leaveGroup(Integer group_id, Integer user_id) {
		Connection connection = DBConnection.getConnection();
		GroupUserDAO.leaveGroup(connection, group_id, user_id);
		closeConnection(connection);
	}
}
