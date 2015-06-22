package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.model.Message;
import com.epam.project.db.transformer.MessageTransformer;
import com.mysql.jdbc.Statement;

public class MessageDAO {

	public static final String SQL_GET_MESSAGE = "SELECT* FROM message WHERE id=?";
	public static final String SQL_GET_ALL_MESSAGE = "SELECT* FROM message";
	public static final String SQL_ADD_NEW_MESSAGE = "Insert into message (subject,content,sender_id)  value(?,?,?)";
	public static final String SQL_UPDATE_MESSAGE = "Update message set subject=?, content=?, sender_id=? where id=?";

	public static final String SQL_UPDATE_TASK = "Update task SET name=?, description=?, deadline=?, available=?,"
			+ " file=?, is_active =?, group_id = ? WHERE id=?";

	public static Message getMessage(Integer id, Connection connection) {

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

	public static Integer addNewMessage(Message message, Connection connection) {

		PreparedStatement stmt;
		Integer key = null;
		Connection con = DBConnection.getConnection();

		try {
			stmt = con.prepareStatement(SQL_ADD_NEW_MESSAGE,
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, message.getSubject());
			stmt.setString(2, message.getContent());
			stmt.setInt(3, message.getSender_id());

			stmt.executeUpdate();
			ResultSet result = stmt.getGeneratedKeys();
			result.next();
			key = result.getInt(1);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return key;
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

	public static List<Message> getUserGroupNotification(Connection connection,
			Integer user_id) {
		PreparedStatement statement = null;
		ResultSet data = null;
		List<Message> messages = null;
		try {
			connection.setAutoCommit(false);
			statement = connection
					.prepareStatement("SELECT * FROM message WHERE id IN "
							+ "(SELECT message_id FROM user_message WHERE is_readed = 0 AND receiver_id = ?);");
			statement.setInt(1, user_id);
			data = statement.executeQuery();
			messages = MessageTransformer.getAllMessages(data);

			statement = connection
					.prepareStatement("UPDATE user_message SET is_readed = 1 WHERE message_id = ?");
			for (Message message : messages) {
				statement.setInt(1, message.getId());
				statement.executeUpdate();
			}
			connection.commit();
			connection.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}

	public static void sendMessageToUsers(Connection connection,
			Integer message_id, List<Integer> users_id) {
		PreparedStatement statement = null;
		try {
			statement = connection
					.prepareStatement("INSERT INTO user_message (message_id, receiver_id) VALUE (?,?);");
			for (Integer user : users_id) {
				statement.setInt(1, message_id);
				statement.setInt(2, user);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void sendMessageToRest(Connection connection,
			Integer message_id, Integer group_id, List<Integer> users_id) {
		if (users_id.isEmpty()) {
			return;
		}
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			List<Integer> rest_users = new ArrayList<Integer>();
			String separator = "";
			String set = "(";
			for (Integer integer : users_id) {
				set += separator;
				set += integer;
				separator = ", ";
			}
			set += ")";

			statement = connection
					.prepareStatement("SELECT user_id from group_user WHERE group_id = ? AND user_id NOT IN "
							+ set);
			statement.setInt(1, group_id);

			result = statement.executeQuery();
			while (result.next()) {
				rest_users.add(result.getInt(1));
			}
			statement = connection
					.prepareStatement("INSERT INTO user_message (message_id, receiver_id) VALUE (?,?);");
			for (Integer integer : rest_users) {
				statement.setInt(1, message_id);
				statement.setInt(2, integer);
				statement.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
