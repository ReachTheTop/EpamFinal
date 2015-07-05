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
	
	@Before
	public void setUp() throws Exception {
		connection =  DBConnection.getConnection();
		connection.setAutoCommit(false);
		savepoint1 = connection.setSavepoint("Savepoint1");		
	}

	@After
	public void tearDown() throws Exception {
		connection.rollback(savepoint1);
	    connection.close();
	}

	@Test
	public void testAddCourse() throws Exception   {
		Event event = new Event();
		event.setNameEvent("name");
		event.setTypeEvent("type");
		event.setDescription("description");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = sdf.parse("2015-12-08");
			event.setDate(d);
			///user.setBirtday(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		event.setGroup_id(32);
		
		Integer id = EventService.newEvent(event);	
		assertNotNull(id);	
		EventService.delete(id);
		//assertNull(EventService.getById(id));
	} 	
	
	@Test
	public void testGetAll() {		
		
		List<Event> events = EventService.getAll();
		assertTrue(events.size() > 0);
	}
}
