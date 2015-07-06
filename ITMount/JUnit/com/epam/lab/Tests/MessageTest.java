package com.epam.lab.Tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Savepoint;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.KnowledgeBaseDAO;
import com.epam.project.db.dao.MessageDAO;
import com.epam.project.db.model.KnowledgeBase;
import com.epam.project.db.model.Message;

public class MessageTest {

	Connection connection;
	Savepoint savepoint1;
	Message message;
	
	@Before
	public void setUp() throws Exception {
		connection =  DBConnection.getConnection();
		connection.setAutoCommit(false);
		savepoint1 = connection.setSavepoint("Savepoint1");	
		
		message = new Message();
		message.setSubject("subject");
		message.setContent("content");
		message.setSender_id(11);
	}

	@After
	public void tearDown() throws Exception {
		connection.rollback(savepoint1);
	    connection.close();
	}

	@Test
	public void testAddMessage() throws Exception   {		
			
		message.setId(MessageDAO.addNewMessage(message, connection));
		assertNotNull(message.getId());	
	} 	
	
	@Test
	public void testUpdateMessage() throws Exception   {
		
		message.setId(MessageDAO.addNewMessage(message, connection));
		message.setSubject("newSubject");
		MessageDAO.updateMessage(message, connection);	
		Message newMessage = new Message();
		newMessage = MessageDAO.getMessage(message.getId(), connection);
		assertEquals("newSubject", newMessage.getSubject());	
	}
	
	@Test
	public void testGetAllMessage() {		
		
		List<Message> messages = MessageDAO.getAllMessages(connection);
		assertTrue(messages.size() > 0);
	}
	
	@Test
	public void testDeleteMessage() throws Exception   {		
		
		
		message.setId(MessageDAO.addNewMessage(message, connection));
		MessageDAO.delMessage(message.getId(),connection);
		Message newMessage = new Message();
		newMessage = MessageDAO.getMessage(message.getId(), connection);;
		assertNull(newMessage);
	}
	
}
