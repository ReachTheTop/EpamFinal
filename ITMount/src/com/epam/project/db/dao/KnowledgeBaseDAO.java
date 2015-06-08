package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.model.KnowledgeBase;
import com.epam.project.db.transformer.KnowledgeBaseTransfomer;

public class KnowledgeBaseDAO {

	public static void addKnowledgeBase(KnowledgeBase kBase, Connection connection){
		try{
		PreparedStatement st = connection.prepareStatement("INSERT INTO knowlegdebase(path,available,is_active,course_id) VALUES(?,?,?,?);");
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
			
			PreparedStatement st = connection.prepareStatement("DELETE FROM knowlegdebase WHERE id=?");
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void updateKnowledgeBase(KnowledgeBase kBase, Connection connection){
		try {
			
			PreparedStatement st = connection.prepareStatement("UPDATE knowlegdebase SET path=?, available=?, is_active=?, course_id=? WHERE id=?");
			
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
		
			PreparedStatement st = connection.prepareStatement("SELECT * FROM knowlegdebase");
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
			
			PreparedStatement st = connection.prepareStatement("SELECT * FROM knowlegdebase WHERE id=?");
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
