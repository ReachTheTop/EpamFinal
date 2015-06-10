package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.model.UserMessage;
import com.epam.project.db.transformer.UserMessageTransformer;

public class UserMessageDAO {

	private static final String NEW_USER_MESSAGE = "INSERT INTO user_message (id, message_id, is_deleted, is_readed, receiver_id) VALUES (?, ?, ?, ?, ?);";
	private static final String GET_ALL = "SELECT * FROM user_message;";
	private static final String GET_BY_ID = "SELECT * FROM user_message WHERE id = ?";
	private static final String UPDATE = "UPDATE user_message SET  message_id = ?, is_deleted = ?, is_readed = ?, receiver_id = ? WHERE id = ?;";
	private static final String DELETE = "UPDATE user_message SET is_deleted = 1 WHERE id = ?;";
	
	public static void addUserMessage(UserMessage userMessage, Connection connection){
		try{
			PreparedStatement st = connection.prepareStatement(NEW_USER_MESSAGE);		
			
			
			st.setInt(1,userMessage.getId());
			st.setInt(2,userMessage.getMessage_id());
			st.setBoolean(3, userMessage.getIs_deleted());
			st.setBoolean(4, userMessage.getIs_readed());
			st.setInt(5, userMessage.getReceiver_id());	
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static List<UserMessage> getAllUserMessage(Connection connection){
		ResultSet rs = null;
		List<UserMessage> list = null;
		try {
		
			PreparedStatement st = connection.prepareStatement(GET_ALL);
			rs = st.executeQuery();
			list = UserMessageTransformer.getAllUserMessage(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static UserMessage getUserMessage(Integer id,Connection connection){
		ResultSet rs = null;
		UserMessage userMessage =null;
		try {
			
			PreparedStatement st = connection.prepareStatement(GET_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			userMessage= UserMessageTransformer.getUserMessage(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userMessage;
	}
	
	
	public static void updateUserMessage(UserMessage userMessage, Connection connection){
		try {
			
			PreparedStatement st = connection.prepareStatement(UPDATE);
			
			
			st.setInt(1,userMessage.getMessage_id());
			st.setBoolean(2, userMessage.getIs_deleted());
			st.setBoolean(3, userMessage.getIs_readed());
			st.setInt(4, userMessage.getReceiver_id());	
			st.setInt(5,userMessage.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteUserMessage(Integer id,  Connection connection){
		
		try {
			
			PreparedStatement st = connection.prepareStatement(DELETE);
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	



}
