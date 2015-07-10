package com.epam.lab.Tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Savepoint;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.FaqCategoryDAO;
import com.epam.project.db.model.FaqCategory;

public class FAQCategoryTest {
	
	Connection connection;
	Savepoint savepoint1;
	FaqCategory faqCategory;
	
	@Before
	public void setUp() throws Exception {
		connection =  DBConnection.getConnection();
		connection.setAutoCommit(false);
		savepoint1 = connection.setSavepoint("Savepoint1");	
		
		faqCategory = new FaqCategory();
		faqCategory.setCategory("category");
	}

	@After
	public void tearDown() throws Exception {
		connection.rollback(savepoint1);
	    connection.close();
	}

	@Test
	public void testAddCategory() throws Exception   {		
			
		faqCategory.setId(FaqCategoryDAO.addCategory(connection, faqCategory));
		assertNotNull(faqCategory.getId());	
	} 	
	
	@Test
	public void testUpdateCategory() throws Exception   {
		
		faqCategory.setId(FaqCategoryDAO.addCategory(connection, faqCategory));
		faqCategory.setCategory("newCategory");
		FaqCategoryDAO.updateCategory(connection, faqCategory);	
		FaqCategory newFaqCategory = new FaqCategory();
		newFaqCategory = FaqCategoryDAO.getCategoryById(connection, faqCategory.getId());
		assertEquals("newCategory", newFaqCategory.getCategory());	
	}
	
	@Test
	public void testGetAllCategories() {		
		
		List<FaqCategory> categories = FaqCategoryDAO.getCategories(connection);
		assertTrue(categories.size() > 0);
	}
	
	@Test
	public void testDeleteCategory() throws Exception   {		
		
		
		faqCategory.setId(FaqCategoryDAO.addCategory(connection, faqCategory));
		FaqCategoryDAO.deleteCategory(connection, faqCategory.getId());
		FaqCategory newFaqCategory = new FaqCategory();
		newFaqCategory = FaqCategoryDAO.getCategoryById(connection, faqCategory.getId());
		assertNull(newFaqCategory);
	}
	
}
