package com.epam.project.db.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.project.db.model.FaqCategory;

public class FaqCategoryTransformer {
	public static FaqCategory getCategory(ResultSet rs) {

		FaqCategory category = null;

		try {
			while (rs.next()) {
				category = new FaqCategory();
				category.setId(rs.getInt("id"));
				category.setCategory(rs.getString("category"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return category;

	}

	public static List<FaqCategory> getAllCategories(ResultSet rs) {
		List<FaqCategory> list = new ArrayList<FaqCategory>();
		FaqCategory category= null;

		try {
			while (rs.next()) {
				category = new FaqCategory();
				category.setId(rs.getInt("id"));
				category.setCategory(rs.getString("category"));
				list.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
	
}
