package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.FeedbackDAO;
import com.epam.project.db.model.Feedback;


public class FeedbackService {

	public static void addFeedback(Feedback feedback) {
		Connection connection =  DBConnection.getConnection();
		FeedbackDAO.addMessage(feedback, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static List<Feedback> getAllFeedbacks() {
		Connection connection =  DBConnection.getConnection();
		List<Feedback> list = FeedbackDAO.getAllMessage(connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static Feedback getFeedback(Integer id) {
		Connection connection =  DBConnection.getConnection();
		Feedback feedback = FeedbackDAO.getFeedback(id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return feedback;
	}
	
	public static void updateFeedback(Feedback feedback) {
		Connection connection =  DBConnection.getConnection();
		FeedbackDAO.updateFeedback(feedback, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void delFeedback(Integer id) {
		Connection connection =  DBConnection.getConnection();
		FeedbackDAO.delFeedback(id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static List<Feedback> getAllMessageFilter(String filter, Boolean active, Boolean send, Boolean importennt){
		Connection connection =  DBConnection.getConnection();
		List<Feedback> list = FeedbackDAO.getAllMessageFilter(filter, active, send, importennt, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public static List<Feedback> getAllSent(){
		Connection connection =  DBConnection.getConnection();
		List<Feedback> list = FeedbackDAO.getAllSent(connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Feedback> getAllTrash(){
		Connection connection =  DBConnection.getConnection();
		List<Feedback> list = FeedbackDAO.getAllTrash(connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public static List<Feedback> getAllNotRead(){
		Connection connection =  DBConnection.getConnection();
		List<Feedback> list = FeedbackDAO.getAllNotRead(connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Feedback> getAllSearch( Boolean active, String line){
		
		Connection connection =  DBConnection.getConnection();
		List<Feedback> list = FeedbackDAO.getAllSearch(active, line, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
public static List<Feedback> getAllSearch( Boolean active,Boolean send, Boolean important, String line){
		
		Connection connection =  DBConnection.getConnection();
		List<Feedback> list = FeedbackDAO.getAllSearch( active, send, important, line, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
public static List<Feedback> getAllImportant(){
	Connection connection =  DBConnection.getConnection();
	List<Feedback> list = FeedbackDAO.getAllImportant(connection);
	try {
		connection.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
}
	
}
