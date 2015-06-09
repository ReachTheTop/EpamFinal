package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.model.Message;
import com.epam.project.db.model.Task;
import com.epam.project.db.transformer.MessageTransformer;

public class MessageDAO {
	
	public static final String SQL_GET_MESSAGE = "SELECT* FROM message WHERE id=?";
	public static final String SQL_GET_ALL_MESSAGE = "SELECT* FROM message";
	public static final String SQL_ADD_NEW_MESSAGE = "Insert into message (subject,content,sender_id)  value(?,?,?)";
	public static final String SQL_UPDATE_MESSAGE = "Update message set subject=?, content=?, sender_id=? where id=?";
	
	public static final String SQL_UPDATE_TASK = "Update task SET name=?, description=?, deadline=?, available=?,"
			+ " file=?, is_active =?, group_id = ? WHERE id=?";

	
	public static Message getMessage(Integer id , Connection connection){
		
		ResultSet rs = null;
		Message message = null;
		try {

			PreparedStatement st = connection.prepareStatement(SQL_GET_MESSAGE);
			st.setInt(1, id);
			rs = st.executeQuery();
			message = MessageTransformer.getMessage(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
		
	}
	
	public static List<Message> getAllMessages(Connection connection) {

		ResultSet rs = null;
		List<Message> list = null;
		try {

			PreparedStatement st = connection
					.prepareStatement(SQL_GET_ALL_MESSAGE);
			rs = st.executeQuery();
			list = MessageTransformer.getAllMessages(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void addNewMessage(Message message, Connection connection) {

		PreparedStatement stmt;

		Connection con = DBConnection.getConnection();

		try {
			stmt = con.prepareStatement(SQL_ADD_NEW_MESSAGE);
			stmt.setString(1, message.getSubject());
			stmt.setString(2, message.getContent());
			stmt.setInt(3, message.getSender_id());
		
			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	
	public static void updateMessage(Message message, Connection connection) {

		try {

			PreparedStatement stmt = connection
					.prepareStatement(SQL_UPDATE_MESSAGE);

			stmt.setString(1, message.getSubject());
			stmt.setString(2, message.getContent());
			stmt.setInt(3, message.getSender_id());
			
			stmt.setInt(4, message.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
