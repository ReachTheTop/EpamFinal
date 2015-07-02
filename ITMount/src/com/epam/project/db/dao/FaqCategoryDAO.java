package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.model.FaqCategory;
import com.epam.project.db.transformer.FaqCategoryTransformer;
import com.mysql.jdbc.Statement;

public class FaqCategoryDAO {
	private static String GET_CATEGORIES = "SELECT * from faq_category;";
	private static String ADD_CATEGORY = "INSERT INTO faq_category(category) VALUE (?)";
	private static String UPDATE_CATEGORY = "UPDATE faq_category SET category = ? WHERE id = ?";

	public static List<FaqCategory> getCategories(Connection connectionn) {
		List<FaqCategory> categories = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		try {
			statement = connectionn.prepareStatement(GET_CATEGORIES);
			set = statement.executeQuery();
			categories = FaqCategoryTransformer.getAllCategories(set);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}

	public static Integer addCategory(Connection connection,
			FaqCategory category) {
		Integer category_id = null;
		ResultSet set = null;
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(ADD_CATEGORY,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, category.getCategory());
			statement.executeUpdate();
			set = statement.getGeneratedKeys();
			set.next();
			category_id = set.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category_id;
	}

	public static void updateCategory(Connection connection,
			FaqCategory category) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(UPDATE_CATEGORY);
			statement.setString(1, category.getCategory());
			statement.setInt(2, category.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
