package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.model.Language;
import com.epam.project.db.transformer.LanguageTransformer;



public class LanguageDAO {
	
	private static final String NEW_LANGUAGE = "INSERT INTO language (name, language, country, file, img, active) VALUES (?, ?, ?, ?,?,?);";
	private static final String GET_ALL = "SELECT * FROM language WHERE active='1'";
	private static final String GET_ALL_ADMIN = "SELECT * FROM language";
	private static final String GET_BY_ID = "SELECT * FROM language WHERE id = ?";
	private static final String GET_BY_CL = "SELECT * FROM language WHERE language = ? and country = ?";
	private static final String UPDATE = "UPDATE language SET  name = ?, language = ?, country = ?, file = ?, img = ?, active = ? WHERE id = ?;";
	private static final String DELETE = "DELETE FROM language WHERE id=?";
	public static void addLanguage(Language language, Connection connection){
		try{
			PreparedStatement st = connection.prepareStatement(NEW_LANGUAGE);		
			
			
		
			st.setString(1,language.getName());
			st.setString(2, language.getLanguage());
			st.setString(3, language.getCountry());
			st.setString(4, language.getFile());
			st.setString(5, language.getImage());
			if (language.isActive() == null) {
				st.setBoolean(6, false);
			} else {
				st.setBoolean(6, language.isActive());
			}
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
	
	public static List<Language> getAllLanguagesByAmin(Connection connection){
		ResultSet rs = null;
		List<Language> list = null;
		try {
		
			PreparedStatement st = connection.prepareStatement(GET_ALL_ADMIN);
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
	
	public static Language getLanguage(String lang, String country,Connection connection){
		ResultSet rs = null;
		Language language =null;
		try {
			
			PreparedStatement st = connection.prepareStatement(GET_BY_CL);
			st.setString(1, lang);
			st.setString(2, country);
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
			st.setString(3, language.getCountry());
			st.setString(4, language.getFile());
			st.setString(5, language.getImage());
			st.setBoolean(6, language.isActive());
			st.setInt(7,language.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void delLanguage(Integer id, Connection connection) {

		try {

			PreparedStatement st = connection.prepareStatement(DELETE);
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
