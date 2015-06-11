package com.epam.project.db.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.project.db.model.Language;

public class LanguageTransformer {
	public static Language getLanguage(ResultSet rs) {

		Language language = null;

		try {
			while (rs.next()) {
				language = new Language();
				language.setId(rs.getInt(1));
				language.setName(rs.getString(2));
				language.setLanguage(rs.getString(3));
				language.setFile(rs.getString(4));
				language.setImage(rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return language;
	}
	
	public static List<Language> getAllLanguages(ResultSet rs) {
		List<Language> list = new ArrayList<Language>();
		Language language = null;

		try {
			
			while (rs.next()) {
				language = new Language();
				language.setId(rs.getInt(1));
				language.setName(rs.getString(2));
				language.setLanguage(rs.getString(3));
				language.setFile(rs.getString(4));
				language.setImage(rs.getString(5));
			
				list.add(language);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

}
