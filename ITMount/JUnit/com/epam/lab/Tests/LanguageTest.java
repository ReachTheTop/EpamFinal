package com.epam.lab.Tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Savepoint;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.LanguageDAO;
import com.epam.project.db.model.Language;


public class LanguageTest {
	
	Connection connection;
	Savepoint savepoint1;
	Language language;
	
	@Before
	public void setUp() throws Exception {
		connection =  DBConnection.getConnection();
		connection.setAutoCommit(false);
		savepoint1 = connection.setSavepoint("Savepoint1");	
		
		language = new Language();
		language.setName("name");
		language.setLanguage("language");		
		language.setFile("file");
		language.setImage("image");
		language.setCountry("country");
	}

	@After
	public void tearDown() throws Exception {
		connection.rollback(savepoint1);
	    connection.close();
	}

	@Test
	public void testAddLanguage() throws Exception   {		
			
		LanguageDAO.addLanguage(language, connection);	
		language = LanguageDAO.getLanguage(language.getLanguage(), language.getCountry(), connection);
		assertNotNull(language);	
	} 	
	
	@Test
	public void testUpdateLanguage() throws Exception   {
		
		LanguageDAO.addLanguage(language, connection);	
		language = LanguageDAO.getLanguage(language.getLanguage(), language.getCountry(), connection);
		language.setLanguage("newLanguage");		
		LanguageDAO.updateLanguage(language, connection);	
		Language newLanguage = new Language();
	    newLanguage = LanguageDAO.getLanguage(language.getId(), connection);
		assertEquals("newLanguage", newLanguage.getLanguage());	
	}
	
	@Test
	public void testGetAllLanguages() {		
		
		List<Language> languages = LanguageDAO.getAllLanguages(connection);
		assertTrue(languages.size() > 0);
	}
	
	@Test
	public void testDeleteLanguage() throws Exception   {		
		
		
		LanguageDAO.addLanguage(language, connection);	
		language = LanguageDAO.getLanguage(language.getLanguage(), language.getCountry(), connection);
		
		
		LanguageDAO.delLanguage(language.getId(),connection);
		Language newLanguage = new Language();
		newLanguage = LanguageDAO.getLanguage(language.getId(),connection);
		assertNull(newLanguage);
	}
	
}
