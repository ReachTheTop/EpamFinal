package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.ContactDAO;
import com.epam.project.db.dao.GroupDAO;
import com.epam.project.db.model.Contact;
import com.epam.project.db.model.Group;
import com.epam.project.db.transformer.ContactTransformer;

public class GroupService {
	
	public static Group getById(Integer id) {
		
		Connection connection =  DBConnection.getConnection();
		Group group = GroupDAO.getGroupById(id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return group;
	}
	
	public static List<Group> getAll() {
		
		Connection connection =  DBConnection.getConnection();
		List<Group> list = GroupDAO.getAll(connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	
	}
	
	
	public static void newGroup(Group group) {
		
		Connection connection =  DBConnection.getConnection();
		GroupDAO.addNewGroupe(group, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void updateGroup(Group group) {
		Connection connection =  DBConnection.getConnection();
		GroupDAO.updateGroup(group, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
