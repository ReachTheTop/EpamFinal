package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.GroupDAO;
import com.epam.project.db.model.Group;
import com.epam.project.db.model.User;

public class GroupService {

	public static void addUserToGroup(User user, Integer id) {
		Connection connection = DBConnection.getConnection();
		GroupDAO.addUser(connection, user, id);
		closeConnection(connection);

	}

	public static void deleteGroup(Integer id) {

		Connection connection = DBConnection.getConnection();
		GroupDAO.delete(id, connection);
		closeConnection(connection);
	}

	public static Group getById(Integer id) {
		Connection connection = DBConnection.getConnection();
		Group group = null;
		group = GroupDAO.getGroupById(id, connection);

		group.setTeacher(UserService.getUser(group.getTeacher_id()));
		group.setCourse(CourseService.getCourse(group.getCourse_id()));
		closeConnection(connection);
		return group;
	}

	public static List<Group> getAll() {
		List<Group> groups = null;
		Connection connection = DBConnection.getConnection();
		groups = GroupDAO.getAll(connection);

		for (Group group : groups) {
			group.setTeacher(UserService.getUser(group.getTeacher_id()));
			group.setCourse(CourseService.getCourse(group.getCourse_id()));

		}
		closeConnection(connection);
		return groups;
	}
	
	public static List<Group> getGroupsUserStudy(Integer id) {
		List<Group> groups = null;
		Connection connection = DBConnection.getConnection();
		groups = GroupDAO.getGroupsUserStudy(connection, id);

		for (Group group : groups) {
			group.setTeacher(UserService.getUser(group.getTeacher_id()));
			group.setCourse(CourseService.getCourse(group.getCourse_id()));

		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groups;
	}

	public static void newGroup(Group group) {

		Connection connection = DBConnection.getConnection();
		GroupDAO.addNewGroupe(group, connection);
		closeConnection(connection);

	}

	public static void updateGroup(Group group) {
		Connection connection = DBConnection.getConnection();
		GroupDAO.updateGroup(group, connection);
		closeConnection(connection);

	}

	public static void confirmGroup(Integer group_id) {
		Connection connection = DBConnection.getConnection();
		GroupDAO.confirmGroup(connection, group_id);
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
