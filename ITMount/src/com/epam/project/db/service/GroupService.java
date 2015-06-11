package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.GroupDAO;
import com.epam.project.db.model.Group;
import com.epam.project.db.model.User;
import com.epam.project.db.transformer.GroupTransformer;

public class GroupService {

	public static void addUserToGroup(User user, Integer id) {
		GroupDAO groupBridge = new GroupDAO();

		groupBridge.addUser(user, id);
		groupBridge.close();
	}

	public static void deleteGroup(Integer id) {

		Connection con = DBConnection.getConnection();
		GroupDAO.delete(id, con);
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Group getById(Integer id) {
		Connection connection = DBConnection.getConnection();
		Group group = null;
		group = GroupDAO.getGroupById(id, connection);

		group.setTeacher(UserService.getUser(group.getTeacher_id()));
		group.setCourse(CourseService.getCourse(group.getCourse_id()));
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void updateGroup(Group group) {
		Connection connection = DBConnection.getConnection();
		GroupDAO.updateGroup(group, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
