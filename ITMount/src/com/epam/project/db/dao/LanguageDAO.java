package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.model.Language;
import com.epam.project.db.transformer.LanguageTransformer;



public class LanguageDAO {
	
	private static final String NEW_LANGUAGE = "INSERT INTO language (id, name, language, file, img) VALUES (?, ?, ?, ?, ?);";
	private static final String GET_ALL = "SELECT * FROM language;";
	private static final String GET_BY_ID = "SELECT * FROM language WHERE id = ?";
	private static final String UPDATE = "UPDATE language SET  name = ?, language = ?, file = ?, img = ? WHERE id = ?;";
	
	public static void addLanguage(Language language, Connection connection){
		try{
			PreparedStatement st = connection.prepareStatement(NEW_LANGUAGE);		
			
			
			st.setInt(1,language.getId());
			st.setString(2,language.getName());
			st.setString(3, language.getLanguage());
			st.setString(4, language.getFile());
			st.setString(5, language.getImage());	
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static List<Language> getAllLanguages(Connection connection){
		ResultSet rs = null;
		List<Language> list = null;
		try {
		
			PreparedStatement st = connection.prepareStatement(GET_ALL);
			rs = st.executeQuery();
			list = LanguageTransformer.getAllLanguages(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static Language getLanguage(Integer id,Connection connection){
		ResultSet rs = null;
		Language language =null;
		try {
			
			PreparedStatement st = connection.prepareStatement(GET_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			language= LanguageTransformer.getLanguage(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return language;
	}
	
	
	public static void updateLanguage(Language language, Connection connection){
		try {
			
			PreparedStatement st = connection.prepareStatement(UPDATE);
			
			
			st.setString(1,language.getName());
			st.setString(2, language.getLanguage());
			st.setString(3, language.getFile());
			st.setString(4, language.getImage());
			st.setInt(5,language.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
