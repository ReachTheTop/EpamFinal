package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.UserMessageDAO;
import com.epam.project.db.model.UserMessage;

public class UserMessageService {
	
	public static void addUserMessage(UserMessage userMessage) {
		Connection connection =  DBConnection.getConnection();
		UserMessageDAO.addUserMessage(userMessage, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static List<UserMessage> getAllUserMessage() {
		Connection connection =  DBConnection.getConnection();
		List<UserMessage> list = UserMessageDAO.getAllUserMessage(connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static UserMessage getUserMessage(Integer id) {
		Connection connection =  DBConnection.getConnection();
		UserMessage userMessage = UserMessageDAO.getUserMessage(id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userMessage;
	}
	
	public static void updateUserMessage(UserMessage userMessage) {
		Connection connection =  DBConnection.getConnection();
		UserMessageDAO.updateUserMessage(userMessage, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteUserMessage(Integer id) {
		Connection connection =  DBConnection.getConnection();
		UserMessageDAO.deleteUserMessage(id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
