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

	public static void updateMessage(Message message) {

		Connection connection = DBConnection.getConnection();
		MessageDAO.updateMessage(message, connection);

		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static List<Message> getUserGroupNotification(Integer user_id) {
		Connection connection = DBConnection.getConnection();
		List<Message> messages = null;
		messages = MessageDAO.getUserGroupNotification(connection, user_id);
		closeConnection(connection);
		return messages;
	}

	private static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendMessageToUsers(Message message, List<Integer> users_id){
		Connection connection = DBConnection.getConnection();
		Integer message_id =  MessageDAO.addNewMessage(message, connection);
		MessageDAO.sendMessageToUsers(connection, message_id, users_id);
		closeConnection(connection);
	}
	
	public static void sendMessageToRest(Message message, Integer group_id , List<Integer> users_id){
		Connection connection = DBConnection.getConnection();
		Integer message_id = MessageDAO.addNewMessage(message, connection);
		MessageDAO.sendMessageToRest(connection, message_id, group_id, users_id);
		closeConnection(connection);
	}

}
