package com.epam.project.db.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.project.db.model.Message;
import com.epam.project.db.model.Task;

public class MessageTransformer {
	
	public static Message getMessage(ResultSet rs) {

		Message message = null;

		try {
			while (rs.next()) {
				message = new Message();
				message.setId(rs.getInt(1));
				message.setSubject(rs.getString(2));
				message.setContent(rs.getString(3));
				message.setSender_id(rs.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

	
	public static List<Message> getAllMessages(ResultSet rs) {
		List<Message> list = new ArrayList<Message>();
		Message message = null;

		try {
			
			while (rs.next()) {
				message = new Message();
				message.setId(rs.getInt(1));
				message.setSubject(rs.getString(2));
				message.setContent(rs.getString(3));
				message.setSender_id(rs.getInt(4));
			
				list.add(message);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
	
}
