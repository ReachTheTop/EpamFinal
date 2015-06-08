package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.model.KnowledgeBase;
import com.epam.project.db.transformer.KnowledgeBaseTransfomer;

public class KnowledgeBaseDAO {

	private static final String INSERT = "INSERT INTO knowlegdebase(path,available,is_active,course_id) VALUES(?,?,?,?);";
	private static final String UPDATE = "UPDATE knowlegdebase SET path=?, available=?, is_active=?, course_id=? WHERE id=?";
	private static final String DELETE = "DELETE FROM knowlegdebase WHERE id=?";
	private static final String SELECTALL = "SELECT * FROM knowlegdebase";
	private static final String SELECT = "SELECT * FROM knowlegdebase WHERE id=?";
	
	public static void addKnowledgeBase(KnowledgeBase kBase, Connection connection){
		try{
		PreparedStatement st = connection.prepareStatement(INSERT);
		st.setString(1,kBase.getPath());
		st.setBoolean(2, kBase.getAvailable());
		st.setBoolean(3, kBase.getIs_active());
		st.setInt(4, kBase.getCourse_id());
		st.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
		
	}
	
	public static void delKnowledgeBase(Integer id,  Connection connection){
	
		try {
			
			PreparedStatement st = connection.prepareStatement(DELETE);
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void updateKnowledgeBase(KnowledgeBase kBase, Connection connection){
		try {
			
			PreparedStatement st = connection.prepareStatement(UPDATE);
			
			st.setString(1,kBase.getPath());
			st.setBoolean(2, kBase.getAvailable());
			st.setBoolean(3, kBase.getIs_active());
			st.setInt(4, kBase.getCourse_id());
			st.setInt(5, kBase.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<KnowledgeBase> getAllKnowledgeBase(Connection connection){
		ResultSet rs = null;
		List<KnowledgeBase> list = null;
		try {
		
			PreparedStatement st = connection.prepareStatement(SELECTALL);
			rs = st.executeQuery();
			list =KnowledgeBaseTransfomer.getAllKnowledgeBase(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static KnowledgeBase getKnowledgeBase(Integer id,Connection connection){
		ResultSet rs = null;
		KnowledgeBase kBase =null;
		try {
			
			PreparedStatement st = connection.prepareStatement(SELECT);
			st.setInt(1, id);
			rs = st.executeQuery();
			kBase= KnowledgeBaseTransfomer.getKnowledgeBase(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kBase;
	}
}
