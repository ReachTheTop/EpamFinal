package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.MessageDAO;
import com.epam.project.db.dao.TaskDAO;
import com.epam.project.db.model.Message;
import com.epam.project.db.model.Task;

public class MessageService {

	public static Message getMessage(Integer id) {

		Connection connection = DBConnection.getConnection();
		Message message = MessageDAO.getMessage(id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return message;

	}

	public static List<Message> getAllMessage() {

		Connection connection = DBConnection.getConnection();
		List<Message> list = MessageDAO.getAllMessages(connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static void addNewMessage(Message message) {

		Connection connection = DBConnection.getConnection();
		MessageDAO.addNewMessage(message, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void updateMessage(Message message){
		
		Connection connection = DBConnection.getConnection();
		MessageDAO.updateMessage(message, connection);
		
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
