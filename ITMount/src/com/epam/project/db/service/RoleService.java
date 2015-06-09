package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.RoleDAO;
import com.epam.project.db.dao.UserDAO;
import com.epam.project.db.model.Role;
import com.epam.project.db.model.User;

public class RoleService {
	
public static Role getRole(Integer id){
		
		Connection connection =  DBConnection.getConnection();
		Role role = RoleDAO.getRole(id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return role;
	}
	
	public static List<Role> getAllRoles() {
		Connection connection =  DBConnection.getConnection();
		List<Role> list = RoleDAO.getAllRoles(connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static void updateRole(Role role){
		
		Connection connection =  DBConnection.getConnection();
		
		RoleDAO.updateRole(role, connection);
		
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void addNewRole(Role role) {

		Connection connection =  DBConnection.getConnection();
		RoleDAO.addNewRole(role,connection);
		
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
