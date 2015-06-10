package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.LanguageDAO;
import com.epam.project.db.model.Language;

public class LanguageService {
	
	public static void addLanguage(Language language) {
		Connection connection =  DBConnection.getConnection();
		LanguageDAO.addLanguage(language, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static List<Language> getAllLanguages() {
		Connection connection =  DBConnection.getConnection();
		List<Language> list = LanguageDAO.getAllLanguages(connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static Language getLanguage(Integer id) {
		Connection connection =  DBConnection.getConnection();
		Language language = LanguageDAO.getLanguage(id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return language;
	}
	
	public static void updateLanguage(Language language) {
		Connection connection =  DBConnection.getConnection();
		LanguageDAO.updateLanguage(language, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
