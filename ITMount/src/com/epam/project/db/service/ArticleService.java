package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.ArticleCorteg;
import com.epam.project.db.dao.ArticleDAO;
import com.epam.project.db.model.Article;

public class ArticleService {

	public static Integer createArticle(Article article) {
		Connection connection = DBConnection.getConnection();
		Integer article_id = null;
		article_id = ArticleDAO.createArticle(connection, article);
		closeConnection(connection);
		return article_id;
	}

	public static ArticleCorteg getAll(String token, Integer page) {
		Connection connection = DBConnection.getConnection();
		ArticleCorteg cortage = null;
		cortage = ArticleDAO.getAll(connection, token, page);
		for (Article article : cortage.getArticles()) {
			article.setAuthor(UserService.getUser(article.getUser_id()));
		}
		closeConnection(connection);
		return cortage;
	}

	public static Article getById(Integer article_id) {
		Connection connection = DBConnection.getConnection();
		Article article = ArticleDAO.getById(connection, article_id);
		article.setAuthor(UserService.getUser(article.getUser_id()));
		closeConnection(connection);
		return article;
	}

	public static void updateArticle(Article article) {
		Connection connection = DBConnection.getConnection();
		ArticleDAO.updateArticle(connection, article);
		closeConnection(connection);
	}

	public static void togleArticle(Integer article_id) {
		Connection connection = DBConnection.getConnection();
		ArticleDAO.togleArticle(connection, article_id);
		closeConnection(connection);
	}

	private static void closeConnection(Connection connection) {
		try {
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Article> getByAuthor(Integer author_id) {
		List<Article> articles = null;
		Connection connection = DBConnection.getConnection();
		articles = ArticleDAO.getByAuthor(connection, author_id);
		closeConnection(connection);
		return articles;
	}

	public static List<Article> getFAQ() {
		Connection connection = DBConnection.getConnection();
		List<Article> qa = null;
		qa = ArticleDAO.getFAQ(connection);
		closeConnection(connection);
		return qa;
	}

	public static Integer createFAQ(Article faq) {
		Integer qa_id = null;
		Connection connection = DBConnection.getConnection();
		qa_id = ArticleDAO.createFAQ(connection, faq);
		closeConnection(connection);
		return qa_id;
	}

	public static Article getFAQById(Integer faq_id) {
		Article question = null;
		Connection connection = DBConnection.getConnection();
		question = ArticleDAO.getFAQById(connection, faq_id);
		closeConnection(connection);
		return question;

	}

	public static void deleteArticle(Integer article_id) {
		Connection connection = DBConnection.getConnection();
		ArticleDAO.deleteArticle(connection, article_id);
		closeConnection(connection);

	}
}
