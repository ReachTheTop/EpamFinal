package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.KnowledgeBaseDAO;
import com.epam.project.db.model.KnowledgeBase;

public class KnowledgeBaseService{

	public static void addKnowledgeBase(KnowledgeBase kBase) {
		Connection connection =  DBConnection.getConnection();
		KnowledgeBaseDAO.addKnowledgeBase(kBase, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void delKnowledgeBase(Integer id) {
		Connection connection =  DBConnection.getConnection();
		KnowledgeBaseDAO.delKnowledgeBase(id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateKnowledgeBase(KnowledgeBase kBase) {
		Connection connection =  DBConnection.getConnection();
		KnowledgeBaseDAO.updateKnowledgeBase(kBase, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<KnowledgeBase> getAllKnowledgeBases() {
		Connection connection =  DBConnection.getConnection();
		List<KnowledgeBase> list = KnowledgeBaseDAO.getAllKnowledgeBase(connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static KnowledgeBase getKnowledgeBase(Integer id) {
		Connection connection =  DBConnection.getConnection();
		KnowledgeBase kBase = KnowledgeBaseDAO.getKnowledgeBase(id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kBase;
	}
	public static KnowledgeBase getKnowledgeBase(String path){
		Connection connection =  DBConnection.getConnection();
		KnowledgeBase kBase = KnowledgeBaseDAO.getKnowledgeBase(path, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kBase;
	}
	public static List<KnowledgeBase> getAllKnowledgeBaseForCourse(Integer course_id){
		Connection connection =  DBConnection.getConnection();
		List<KnowledgeBase> list = KnowledgeBaseDAO.getAllKnowledgeBaseForCourse(course_id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	
	}
	
	public static List<KnowledgeBase> getAllKnowledgeBaseForCourseByActive(Integer course_id){
		Connection connection =  DBConnection.getConnection();
		List<KnowledgeBase> list = KnowledgeBaseDAO.getAllKnowledgeBaseForCourseByActive(course_id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	
	}
}
