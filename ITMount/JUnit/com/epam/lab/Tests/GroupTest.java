package com.epam.lab.Tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Savepoint;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.GroupDAO;
import com.epam.project.db.model.Group;

public class GroupTest {
	
	Connection connection;
	Savepoint savepoint1;
	Group group;
	
	@Before
	public void setUp() throws Exception {
		connection =  DBConnection.getConnection();
		connection.setAutoCommit(false);
		savepoint1 = connection.setSavepoint("Savepoint1");	
		
		group = new Group();
		group.setCourse_id(23);
		group.setTeacher_id(11);
		group.setName("name");
		group.setIs_active(true);
	}

	@After
	public void tearDown() throws Exception {
		connection.rollback(savepoint1);
	    connection.close();
	}

	@Test
	public void testAddGroup() throws Exception   {		
			
		group.setId(GroupDAO.addNewGroupe(group, connection));	
		assertNotNull(group.getId());	
	} 	
	
	@Test
	public void testUpdateGroup() throws Exception   {
		
		group.setId(GroupDAO.addNewGroupe(group, connection));	
		group.setName("newName");	
		GroupDAO.updateGroup(group, connection);	
		Group newGroup = new Group();
		newGroup = GroupDAO.getGroupById(group.getId(), connection);
		assertEquals("newName", newGroup.getName());	
	}
	
	@Test
	public void testGetAllGroup() {		
		
		List<Group> courses = GroupDAO.getAllActiveGroups(connection);
		assertTrue(courses.size() > 0);
	}
	
	@Test
	public void testDeleteGroup() throws Exception   {		
		
		
		group.setId(GroupDAO.addNewGroupe(group, connection));	
		GroupDAO.delete(group.getId(),connection);
		Group newGroup = new Group();
		newGroup = GroupDAO.getGroupById(group.getId(), connection);
		assertFalse(newGroup.getIs_active());
		
	}
	
}
