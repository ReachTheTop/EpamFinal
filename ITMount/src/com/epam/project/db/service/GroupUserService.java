package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.GroupDAO;
import com.epam.project.db.dao.GroupUserDAO;
import com.epam.project.db.model.Group;
import com.epam.project.db.model.GroupUser;

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

	public static List<GroupUser> getAllGroupUSer() {

		Connection connection = DBConnection.getConnection();
		List<GroupUser> list = GroupUserDAO.getAllGroupUser(connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

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
		Connection connection =  DBConnection.getConnection();
		GroupUserDAO.updateGroupUser(groupUser, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
