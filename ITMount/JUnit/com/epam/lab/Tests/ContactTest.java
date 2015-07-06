package com.epam.lab.Tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Savepoint;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.ContactDAO;
import com.epam.project.db.model.Contact;


public class ContactTest {
	
	Connection connection;
	Savepoint savepoint1;
	Contact contact;
	
	@Before
	public void setUp() throws Exception {
		connection =  DBConnection.getConnection();
		connection.setAutoCommit(false);
		savepoint1 = connection.setSavepoint("Savepoint1");	
		
		contact = new Contact();
		contact.setPhone("12345678");
		contact.setSkype("skype");
		contact.setUser_id(82);
		
	}

	@After
	public void tearDown() throws Exception {
		connection.rollback(savepoint1);
	    connection.close();
	}

	@Test
	public void testAddContact() throws Exception   {		
			
		ContactDAO.addContact(contact, connection);	
		assertNotNull(ContactDAO.getByUserId(connection, contact.getUser_id()));	
	} 	
	
	@Test
	public void testUpdateContact() throws Exception   {
		
		ContactDAO.addContact(contact, connection);	
		contact.setId(ContactDAO.getByUserId(connection, contact.getUser_id()).getId());
		contact.setSkype("newSkype");
		ContactDAO.updateContact(contact, connection);	
		Contact newContact = new Contact();
		newContact = ContactDAO.getByUserId(connection, contact.getUser_id());
		assertEquals("newSkype", newContact.getSkype());	
	}
	
	@Test
	public void testGetAllContact() {		
		
		List<Contact> contacts = ContactDAO.getAllContact(connection);
		assertTrue(contacts.size() > 0);
	}
	
	@Test
	public void testDeleteContact() throws Exception   {			
		
		ContactDAO.addContact(contact, connection);	
		contact.setId(ContactDAO.getByUserId(connection, contact.getUser_id()).getId());
		
		ContactDAO.delContact(contact.getId(), connection);	
		Contact newContact = new Contact();
		newContact = ContactDAO.getByUserId(connection, contact.getUser_id());
		assertNull(newContact);
	}
	
}
