package com.epam.project.db.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.project.db.model.Article;



public class ArticleTransformer {

	public static Article getArticle(ResultSet rs) {

		Article article = null;

		try {
			while (rs.next()) {
				article= new Article();
				article.setId(rs.getInt("id"));
				article.setHeader(rs.getString("header"));
				article.setData(rs.getString("data"));
				article.setUser_id(rs.getInt("user_id"));
				article.setCourse_id(rs.getInt("course_id"));
				article.setDate(rs.getTimestamp("date"));
				article.setIs_active(rs.getBoolean("is_active"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return article;

	}

	public static List<Article> getAllArticles(ResultSet rs) {
		List<Article> list = new ArrayList<Article>();
		Article article= null;

		try {
			while (rs.next()) {
				article= new Article();
				article.setId(rs.getInt("id"));
				article.setHeader(rs.getString("header"));
				article.setData(rs.getString("data"));
				article.setUser_id(rs.getInt("user_id"));
				article.setCourse_id(rs.getInt("course_id"));
				article.setDate(rs.getTimestamp("date"));
				article.setIs_active(rs.getBoolean("is_active"));
				list.add(article);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
	
}
