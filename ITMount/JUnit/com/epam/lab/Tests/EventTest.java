package com.epam.lab.Tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Savepoint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.model.Event;
import com.epam.project.db.service.EventService;


public class EventTest {
	
	Connection connection;
	Savepoint savepoint1;
	Event event;
	
	@Before
	public void setUp() throws Exception {
		connection =  DBConnection.getConnection();
		connection.setAutoCommit(false);
		savepoint1 = connection.setSavepoint("Savepoint1");		
		
		event = new Event();
		event.setNameEvent("name");
		event.setTypeEvent("type");
		event.setDescription("description");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = sdf.parse("2015-12-08");
			event.setDate(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		event.setGroup_id(32);
	}

	@After
	public void tearDown() throws Exception {
		connection.rollback(savepoint1);
	    connection.close();
	}

	@Test
	public void testAddEvent() throws Exception   {
		//		
		Integer id = EventService.newEvent(event);	
		assertNotNull(id);	
	} 	
	
	@Test
	public void testUpdateEvent() throws Exception   {
		
		event.setId(EventService.newEvent(event));
		event.setNameEvent("newName");		
		EventService.update(event);	
		Event newEvent = new Event();
		newEvent = EventService.getById(event.getId());
		assertEquals("newName", newEvent.getNameEvent());	
	}
	
	@Test
	public void testGetAll() {		
		
		List<Event> events = EventService.getAll();
		assertTrue(events.size() > 0);
	}
	
	@Test
	public void testDeleteEvent() throws Exception   {
		
		
		Integer id = EventService.newEvent(event);		
		EventService.completelyRemove("name");
		assertNull(EventService.getById(id));
	} 
}
