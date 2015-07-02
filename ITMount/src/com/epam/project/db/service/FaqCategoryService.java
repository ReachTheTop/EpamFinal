package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.ArticleDAO;
import com.epam.project.db.dao.FaqCategoryDAO;
import com.epam.project.db.model.FaqCategory;

public class FaqCategoryService {
	public static List<FaqCategory> getAllCategories() {
		List<FaqCategory> categories = null;
		Connection connection = DBConnection.getConnection();
		categories = FaqCategoryDAO.getCategories(connection);
		closeConnection(connection);
		return categories;
	}
	
	public static List<FaqCategory> getAllCategoriesWithQA(){
		List<FaqCategory> data = null;
		Connection connection = DBConnection.getConnection();
		data = FaqCategoryDAO.getCategories(connection);
		for (FaqCategory faqCategory : data) {
			faqCategory.setFaq(ArticleDAO.getFAQByCategory(connection, faqCategory.getId()));
		}
		closeConnection(connection);
		return data;
	}
	
	public static Integer addCategory(FaqCategory category){
		Integer category_id = null;
		Connection connection = DBConnection.getConnection();
		category_id = FaqCategoryDAO.addCategory(connection, category);
		closeConnection(connection);
		return category_id;
	}
	
	public static void updateCategory(FaqCategory category){
		Connection connection = DBConnection.getConnection();
		FaqCategoryDAO.updateCategory(connection, category);
		closeConnection(connection);
	}

	private static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
