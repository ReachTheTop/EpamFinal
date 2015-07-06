package com.epam.lab.Tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Savepoint;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.KnowledgeBaseDAO;
import com.epam.project.db.model.KnowledgeBase;

public class KnowledgebaseTest {
	Connection connection;
	Savepoint savepoint1;
	KnowledgeBase kBase;
	
	@Before
	public void setUp() throws Exception {
		connection =  DBConnection.getConnection();
		connection.setAutoCommit(false);
		savepoint1 = connection.setSavepoint("Savepoint1");	
		
		kBase = new KnowledgeBase();
		kBase.setPath("path");
		kBase.setAvailable(true);
		kBase.setIs_active(true);
		kBase.setCourse_id(23);
	}

	@After
	public void tearDown() throws Exception {
		connection.rollback(savepoint1);
	    connection.close();
	}

	@Test
	public void testAddKnowledgeBase() throws Exception   {		
			
		KnowledgeBaseDAO.addKnowledgeBase(kBase, connection);	
		kBase = KnowledgeBaseDAO.getSingleKnowledgeBaseForCourseAndPath(kBase.getCourse_id(), kBase.getPath(), connection);
		assertNotNull(kBase.getId());	
	} 	
	
	@Test
	public void testUpdateKnowledgeBase() throws Exception   {
		
		KnowledgeBaseDAO.addKnowledgeBase(kBase, connection);	
		kBase = KnowledgeBaseDAO.getSingleKnowledgeBaseForCourseAndPath(kBase.getCourse_id(), kBase.getPath(), connection);
		kBase.setPath("newPath");	
		KnowledgeBaseDAO.updateKnowledgeBase(kBase, connection);	
		KnowledgeBase newKBase = new KnowledgeBase();
		newKBase = KnowledgeBaseDAO.getKnowledgeBase(kBase.getId(), connection);
		assertEquals("newPath", newKBase.getPath());	
	}
	
	@Test
	public void testGetAllKnowledgeBase() {		
		
		List<KnowledgeBase> kBases = KnowledgeBaseDAO.getAllKnowledgeBase(connection);
		assertTrue(kBases.size() > 0);
	}
	
	@Test
	public void testDeleteKnowledgeBase() throws Exception   {		
		
		
		KnowledgeBaseDAO.addKnowledgeBase(kBase, connection);	
		kBase = KnowledgeBaseDAO.getSingleKnowledgeBaseForCourseAndPath(kBase.getCourse_id(), kBase.getPath(), connection);

		KnowledgeBaseDAO.delKnowledgeBase(kBase.getId(),connection);
		KnowledgeBase newKBase = new KnowledgeBase();
		newKBase = KnowledgeBaseDAO.getKnowledgeBase(kBase.getId(), connection);
		assertNull(newKBase);
	}
	
}
