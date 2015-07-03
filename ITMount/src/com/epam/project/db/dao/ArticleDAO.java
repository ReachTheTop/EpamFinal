package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.model.Article;
import com.epam.project.db.transformer.ArticleTransformer;
import com.mysql.jdbc.Statement;

public class ArticleDAO {

	private static final String CREATE_ARTICLE = "INSERT INTO article (data, header, course_id, user_id) VALUE (?,?,?,?)";

	private static final String GET_BY_ID = "SELECT * FROM article WHERE id = ?";
	private static final String UPDATE_ARTICLE = "UPDATE article SET header= ?, data= ?, course_id = ? WHERE id = ?;";
	private static final String TOGLE_ARTICLE = "UPDATE article SET is_active = !is_active WHERE id = ?;";

	public static Integer createArticle(Connection connection, Article article) {
		PreparedStatement statement = null;
		Integer article_id = null;
		try {
			statement = connection.prepareStatement(CREATE_ARTICLE,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, article.getData());
			statement.setString(2, article.getHeader());
			statement.setInt(3, article.getCourse_id());
			statement.setInt(4, article.getUser_id());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			article_id = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article_id;
	}

	public static ArticleCorteg getAll(Connection connection, String token,
			Integer page) {
		String pattern = String.format("%s.*", token);
		String coursePattern = String.format("^(%s)$", token);
		String authorPattern = String.format("^(%s)$", token);
		ArticleCorteg cortage = new ArticleCorteg();

		PreparedStatement statement = null;

		ResultSet rs = null;
		try {
			connection.setAutoCommit(false);
			statement = connection
					.prepareStatement("SELECT SQL_CALC_FOUND_ROWS * FROM article "
							+ "WHERE type = 'article' AND is_active = 1 AND (header REGEXP ? OR "
							+ "course_id IN (SELECT id FROM course WHERE name REGEXP ?) "
							+ "OR user_id IN (SELECT id FROM user WHERE surname REGEXP ?)) ORDER BY date DESC LIMIT ?,?;");
			statement.setString(1, pattern);
			statement.setString(2, coursePattern);
			statement.setString(3, authorPattern);
			statement.setInt(4, page * 5);
			statement.setInt(5, 5);
			rs = statement.executeQuery();
			cortage.setArticles(ArticleTransformer.getAllArticles(rs));
			statement = connection.prepareStatement("SELECT found_rows();");
			rs = statement.executeQuery();
			rs.next();
			cortage.setAmount(rs.getInt(1));
			connection.commit();
			connection.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cortage;
	}

	public static Article getById(Connection connection, Integer article_id) {
		PreparedStatement statement = null;
		Article article = null;
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(GET_BY_ID);
			statement.setInt(1, article_id);
			rs = statement.executeQuery();
			article = ArticleTransformer.getArticle(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;

	}

	public static void updateArticle(Connection connection, Article article) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(UPDATE_ARTICLE);
			statement.setString(1, article.getHeader());
			statement.setString(2, article.getData());
			statement.setInt(3, article.getCourse_id());
			statement.setInt(4, article.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void togleArticle(Connection connection, Integer article_id) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(TOGLE_ARTICLE);
			statement.setInt(1, article_id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Article> getByAuthor(Connection connection,
			Integer author_id) {
		List<Article> articles = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		try {
			statement = connection
					.prepareStatement("SELECT * FROM article WHERE type = 'article' AND user_id = ?;");
			statement.setInt(1, author_id);
			set = statement.executeQuery();
			articles = ArticleTransformer.getAllArticles(set);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}

	public static List<Article> getFAQ(Connection connection) {
		List<Article> qa = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		try {
			statement = connection
					.prepareStatement("SELECT * FROM article WHERE type = 'faq' ");
			set = statement.executeQuery();
			qa = ArticleTransformer.getAllArticles(set);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qa;
	}

	public static Integer createFAQ(Connection connection, Article faq) {
		Integer qa_id = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		try {
			statement = connection
					.prepareStatement(
							"INSERT INTO article (header, course_id, data, type) VALUE (?,?,?, 'faq')",
							Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, faq.getHeader());
			statement.setInt(2, faq.getCourse_id());
			statement.setString(3, faq.getData());
			statement.executeUpdate();
			set = statement.getGeneratedKeys();
			set.next();
			qa_id = set.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qa_id;
	}

	public static List<Article> getFAQByCategory(Connection connection,
			Integer category_id) {
		List<Article> qa = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		try {
			statement = connection
					.prepareStatement("SELECT * FROM article WHERE type = 'faq' AND course_id = ? ");
			statement.setInt(1, category_id);
			set = statement.executeQuery();
			qa = ArticleTransformer.getAllArticles(set);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qa;
	}

	public static Article getFAQById(Connection connection, Integer faq_id) {
		PreparedStatement statement = null;
		ResultSet set = null;
		Article question = null;
		try {
			statement = connection.prepareStatement("SELECT * FROM article WHERE type = 'faq' AND id = ?");
			statement.setInt(1, faq_id);
			set = statement.executeQuery();
			question = ArticleTransformer.getArticle(set);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return question;
	}
}
