package com.epam.lab.Tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.Date;
import java.sql.Savepoint;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.FeedbackDAO;
import com.epam.project.db.model.Feedback;

public class FeedbackTest {

	Connection connection;
	Savepoint savepoint1;
	Feedback feedback;
	
	@Before
	public void setUp() throws Exception {
		connection =  DBConnection.getConnection();
		connection.setAutoCommit(false);
		savepoint1 = connection.setSavepoint("Savepoint1");	
		
		feedback = new Feedback();
		feedback.setName("name");
		feedback.setEmail("email");
		feedback.setType("type");
		feedback.setContent("content");
		feedback.setTimeMessage(new Date());
		feedback.setRead(true);
		feedback.setActive(true);
		feedback.setImportant(true);
		feedback.setSend(true);
	}

	@After
	public void tearDown() throws Exception {
		connection.rollback(savepoint1);
	    connection.close();
	}

	@Test
	public void testAddFeedback() throws Exception   {		
		
		FeedbackDAO.addMessage(feedback, connection);	
		feedback = FeedbackDAO.getMessageByEmailAndDate(connection, feedback.getEmail(), feedback.getTimeMessage());
		assertNotNull(feedback.getId());	
	} 	
	
	@Test
	public void testUpdateFeedback() throws Exception   {
		
		FeedbackDAO.addMessage(feedback, connection);	
		feedback = FeedbackDAO.getMessageByEmailAndDate(connection, feedback.getEmail(), feedback.getTimeMessage());
		feedback.setContent("newContent");
		FeedbackDAO.updateFeedback(feedback, connection);	
		Feedback newFeedback = new Feedback();
		newFeedback = FeedbackDAO.getFeedback(feedback.getId(), connection);
		assertEquals("newContent", newFeedback.getContent());	
	}
	
	@Test
	public void testGetAllFeedbacks() {		
		
		List<Feedback> feedbacks = FeedbackDAO.getAllMessage(connection);
		assertTrue(feedbacks.size() > 0);
	}
	
	@Test
	public void testDeleteFeedback() throws Exception   {		
		
		
		FeedbackDAO.addMessage(feedback, connection);	
		feedback = FeedbackDAO.getMessageByEmailAndDate(connection, feedback.getEmail(), feedback.getTimeMessage());
		FeedbackDAO.delFeedback(feedback.getId(), connection);
		Feedback newFeedback = new Feedback();
		newFeedback = FeedbackDAO.getFeedback(feedback.getId(), connection);
		assertNull(newFeedback);
	}
	
}
