package com.epam.lab.Tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Savepoint;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.JournalDAO;
import com.epam.project.db.model.Journal;

public class JournalTest {

	Connection connection;
	Savepoint savepoint1;
	Journal journal;
	
	@Before
	public void setUp() throws Exception {
		connection =  DBConnection.getConnection();
		connection.setAutoCommit(false);
		savepoint1 = connection.setSavepoint("Savepoint1");	
		
		journal = new Journal();
		journal.setGroupID(36);
		journal.setUserID(6);
		journal.setDate(new Date());
		journal.setVisit(false);
		journal.setDescription("description");
		
	}

	@After
	public void tearDown() throws Exception {
		connection.rollback(savepoint1);
	    connection.close();
	}

	@Test
	public void testAddJournal() throws Exception   {		
		
		JournalDAO.addNewJournal(journal, connection);	
		journal = JournalDAO.getJournalByUserAndDate(journal.getUserID(), journal.getDate(), connection);
		assertNotNull(journal.getId());	
	} 	
	
	@Test
	public void testUpdateJournal() throws Exception   {
		
		JournalDAO.addNewJournal(journal, connection);	
		journal = JournalDAO.getJournalByUserAndDate(journal.getUserID(), journal.getDate(), connection);
		journal.setDescription("newDescription");
		JournalDAO.updateJournal(journal, connection);	
		Journal newJournal = new Journal();
		newJournal = JournalDAO.getJournal(journal.getId(), connection);
		assertEquals("newDescription", newJournal.getDescription());	
	}
	
	@Test
	public void testGetAllJournals() {		
		
		List<Journal> journals = JournalDAO.getAllJournals(connection);
		assertTrue(journals.size() > 0);
	}
	
	@Test
	public void testDeleteJournal() throws Exception   {		
		
		
		JournalDAO.addNewJournal(journal, connection);	
		journal = JournalDAO.getJournalByUserAndDate(journal.getUserID(), journal.getDate(), connection);
		JournalDAO.delJournal(journal.getId(), connection);
		Journal newJournal = new Journal();
		newJournal = JournalDAO.getJournal(journal.getId(), connection);
		assertNull(newJournal);
	}
	
}
