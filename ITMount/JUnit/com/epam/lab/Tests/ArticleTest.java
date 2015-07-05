package com.epam.lab.Tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Savepoint;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.model.Article;
import com.epam.project.db.service.ArticleService;


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
			
		Integer id = ArticleService.createArticle(article);	
		assertNotNull(id);	
	} 	
	
	@Test
	public void testUpdateArticle() throws Exception   {
		
		article.setId(ArticleService.createArticle(article));	
		article.setHeader("newHeader");	
		ArticleService.updateArticle(article);	
		Article newArticle = new Article();
		newArticle = ArticleService.getByIdWithoutUser(article.getId());
		assertEquals("newHeader", newArticle.getHeader());	
	}
	
	@Test
	public void testGetByAuthor() {		
		
		List<Article> articles = ArticleService.getByAuthor(25);
		assertTrue(articles.size() > 0);
	}
	
	@Test
	public void testDeleteteArticle() throws Exception   {			
		
		article.setId(ArticleService.createArticle(article));	
		
		ArticleService.deleteArticle(article.getId());	
		Article newArticle = new Article();
		newArticle = ArticleService.getByIdWithoutUser(article.getId());
		assertNull(newArticle);
	}
	
}
