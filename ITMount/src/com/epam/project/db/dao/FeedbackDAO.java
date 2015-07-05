package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.epam.project.db.model.Feedback;
import com.epam.project.db.transformer.FeedbackTransformer;


public class FeedbackDAO {

	
	private static final String NEW_MESSAGE = "INSERT INTO feedback (name, email, type, content, timeMessage, send) VALUES (?, ?,?, ?, ?, ?);";
	private static final String GET_ALL = "SELECT * FROM feedback WHERE active = '1' and send ='0' and important !='1'";
	private static final String GET_BY_ID = "SELECT * FROM feedback WHERE id = ?";
	private static final String UPDATE = "UPDATE feedback f SET  name = ?, email = ?, type = ?, content = ?, timeMessage = ?, f.read = ? ,active = ?, important = ?, send = ? WHERE id = ?;";
	private static final String DELETE = "DELETE FROM feedback WHERE id=?";
	private static final String GET_ALL_FILTER = "SELECT * FROM feedback WHERE type = ? and active = ? and send =? and important =?";
	private static final String GET_ALL_SENT = "SELECT * FROM feedback WHERE active = '1' and send = '1' and important !='1'";
	private static final String GET_ALL_TRASH = "SELECT * FROM feedback WHERE active = '0'";
	private static final String GET_ALL_READ = "SELECT * FROM feedback f WHERE active = '1' AND f.read='0'  and send='0'";
	private static final String GET_ALL_IMPORTANT = "SELECT * FROM feedback f WHERE active = '1' AND important='1'";
	private static final String GET_SEARCH = "SELECT * FROM feedback WHERE active = ?  and  (name = ? or email = ? or type = ? or content LIKE ?)";
	private static final String GET_SEARCH_ALL = "SELECT * FROM feedback WHERE active = ? and send = ? and important = ? and  (name = ? or email = ? or type = ? or content LIKE ?)";
	private static final String GET_SEARCH_ALL_IMPORTANT = "SELECT * FROM feedback WHERE active = ? and important = ? and  (name = ? or email = ? or type = ? or content LIKE ?)";
	private static final String GET_ALL_FILTER_TRASH = "SELECT * FROM feedback WHERE type = ? and active = ?";
	private static final String GET_ALL_FILTER_IMPORTANT = "SELECT * FROM feedback WHERE type = ? and active = ? and important =?";
	public static void addMessage(Feedback feedback, Connection connection){
		try{
			PreparedStatement st = connection.prepareStatement(NEW_MESSAGE);		
			
			
		
			st.setString(1,feedback.getName());
			st.setString(2, feedback.getEmail());
			st.setString(3, feedback.getType());
			st.setString(4, feedback.getContent());
			st.setTimestamp(5, new Timestamp(feedback.getTimeMessage().getTime()));
			st.setBoolean(6, feedback.getSend());
			/*st.setBoolean(7, feedback.getActive());*/
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static List<Feedback> getAllMessage(Connection connection){
		ResultSet rs = null;
		List<Feedback> list = null;
		try {
		
			PreparedStatement st = connection.prepareStatement(GET_ALL);
			rs = st.executeQuery();
			list = FeedbackTransformer.getAllFeedbacks(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Feedback> getAllMessageFilter(String filter, Boolean active, Boolean send, Boolean importennt, Connection connection){
		ResultSet rs = null;
		List<Feedback> list = null;
		try {
			PreparedStatement st;
			if(send==null&& importennt==null){
				st = connection.prepareStatement(GET_ALL_FILTER_TRASH);
				st.setString(1, filter);
				st.setBoolean(2, active);
				
			}else if(send==null){
				st = connection.prepareStatement(GET_ALL_FILTER_IMPORTANT);
				st.setString(1, filter);
				st.setBoolean(2, active);
				st.setBoolean(3, importennt);
			}else{
				st = connection.prepareStatement(GET_ALL_FILTER);
				st.setString(1, filter);
				st.setBoolean(2, active);
				st.setBoolean(3, send);
				st.setBoolean(4, importennt);
			}
			
			rs = st.executeQuery();
			list = FeedbackTransformer.getAllFeedbacks(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	

	public static Feedback getFeedback(Integer id,Connection connection){
		ResultSet rs = null;
		Feedback feedback =null;
		try {
			
			PreparedStatement st = connection.prepareStatement(GET_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			feedback= FeedbackTransformer.getFeedback(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return feedback;
	}
	
	
	
	public static void updateFeedback(Feedback feedback, Connection connection){
		try {
			
			PreparedStatement st = connection.prepareStatement(UPDATE);
			
			
			st.setString(1,feedback.getName());
			st.setString(2, feedback.getEmail());
			st.setString(3, feedback.getType());
			st.setString(4, feedback.getContent());
			st.setTimestamp(5, new Timestamp(feedback.getTimeMessage().getTime()));
			st.setBoolean(6, feedback.getRead());
			st.setBoolean(7, feedback.getActive());
			st.setBoolean(8, feedback.getImportant());
			st.setBoolean(9, feedback.getSend());
			st.setInt(10, feedback.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void delFeedback(Integer id, Connection connection) {

		try {

			PreparedStatement st = connection.prepareStatement(DELETE);
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static List<Feedback> getAllSent(Connection connection){
		ResultSet rs = null;
		List<Feedback> list = null;
		try {
		
			PreparedStatement st = connection.prepareStatement(GET_ALL_SENT);
			rs = st.executeQuery();
			list = FeedbackTransformer.getAllFeedbacks(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Feedback> getAllTrash(Connection connection){
		ResultSet rs = null;
		List<Feedback> list = null;
		try {
		
			PreparedStatement st = connection.prepareStatement(GET_ALL_TRASH);
			rs = st.executeQuery();
			list = FeedbackTransformer.getAllFeedbacks(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Feedback> getAllNotRead(Connection connection){
		ResultSet rs = null;
		List<Feedback> list = null;
		try {
		
			PreparedStatement st = connection.prepareStatement(GET_ALL_READ);
			rs = st.executeQuery();
			list = FeedbackTransformer.getAllFeedbacks(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static List<Feedback> getAllSearch( Boolean active, String line, Connection connection){
		ResultSet rs = null;
		List<Feedback> list = null;
		try {
		
			PreparedStatement st = connection.prepareStatement(GET_SEARCH);
			st.setBoolean(1, active);
			st.setString(2, line);
			st.setString(3, line);
			st.setString(4, line);
			st.setString(5, "%"+line+"%");
			rs = st.executeQuery();
			list = FeedbackTransformer.getAllFeedbacks(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Feedback> getAllSearch( Boolean active, Boolean send, Boolean important, String line, Connection connection){
		ResultSet rs = null;
		List<Feedback> list = null;
		try {
			PreparedStatement st;
		if(send==null){
			st = connection.prepareStatement(GET_SEARCH_ALL_IMPORTANT);
			st.setBoolean(1, active);
			st.setBoolean(2,important);
			st.setString(3, line);
			st.setString(4, line);
			st.setString(5, line);
			st.setString(6, "%"+line+"%");
		}else{
			st = connection.prepareStatement(GET_SEARCH_ALL);
			st.setBoolean(1, active);
			st.setBoolean(2,send);
			st.setBoolean(3,important);
			st.setString(4, line);
			st.setString(5, line);
			st.setString(6, line);
			st.setString(7, "%"+line+"%");
		}
		
			rs = st.executeQuery();
			list = FeedbackTransformer.getAllFeedbacks(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Feedback> getAllImportant(Connection connection){
		ResultSet rs = null;
		List<Feedback> list = null;
		try {
		
			PreparedStatement st = connection.prepareStatement(GET_ALL_IMPORTANT);
			rs = st.executeQuery();
			list = FeedbackTransformer.getAllFeedbacks(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
