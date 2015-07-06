package com.epam.lab.Tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Savepoint;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.ArticleDAO;
import com.epam.project.db.model.Article;


public class ArticleTest {
	
	Connection connection;
	Savepoint savepoint1;
	Article article;
	
	@Before
	public void setUp() throws Exception {
		connection =  DBConnection.getConnection();
		connection.setAutoCommit(false);
		savepoint1 = connection.setSavepoint("Savepoint1");	
		
		article = new Article();
		article.setHeader("header");
		article.setData("data");
		article.setCourse_id(23);
		article.setUser_id(25);
	}

	@After
	public void tearDown() throws Exception {
		connection.rollback(savepoint1);
	    connection.close();
	}

	@Test
	public void testAddArticle() throws Exception   {		
			
		Integer id = ArticleDAO.createArticle(connection, article);	
		assertNotNull(id);	
	} 	
	
	@Test
	public void testUpdateArticle() throws Exception   {
		
		article.setId(ArticleDAO.createArticle(connection, article));	
		article.setHeader("newHeader");	
		ArticleDAO.updateArticle(connection, article);	
		Article newArticle = new Article();
		newArticle = ArticleDAO.getById(connection, article.getId());
		assertEquals("newHeader", newArticle.getHeader());	
	}
	
	@Test
	public void testGetByAuthor() {		
		
		List<Article> articles = ArticleDAO.getByAuthor(connection, 13);
		assertTrue(articles.size() > 0);
	}
	
	@Test
	public void testDeleteteArticle() throws Exception   {			
		
		article.setId(ArticleDAO.createArticle(connection, article));	
		
		ArticleDAO.deleteArticle(connection, article.getId());	
		Article newArticle = new Article();
		newArticle = ArticleDAO.getById(connection, article.getId());
		assertNull(newArticle);
	}
	
}
