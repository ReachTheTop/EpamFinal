package com.epam.project.db.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.project.db.model.UserMessage;



public class UserMessageTransformer {
	
	public static UserMessage getUserMessage(ResultSet rs) {

		UserMessage userMessage = null;

		try {
			while (rs.next()) {
				userMessage = new UserMessage();
				userMessage.setId(rs.getInt(1));
				userMessage.setMessage_id(rs.getInt(2));
				userMessage.setIs_deleted(rs.getBoolean(3));
				userMessage.setIs_readed(rs.getBoolean(4));
				userMessage.setReceiver_id(rs.getInt(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userMessage;
	}
	
	public static List<UserMessage> getAllUserMessage(ResultSet rs) {
		List<UserMessage> list = new ArrayList<UserMessage>();
		UserMessage userMessage = null;

		try {
			
			while (rs.next()) {
				userMessage = new UserMessage();
				userMessage.setId(rs.getInt(1));
				userMessage.setMessage_id(rs.getInt(2));
				userMessage.setIs_deleted(rs.getBoolean(3));
				userMessage.setIs_readed(rs.getBoolean(4));
				userMessage.setReceiver_id(rs.getInt(5));
			
				list.add(userMessage);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

}
