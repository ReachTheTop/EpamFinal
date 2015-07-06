package com.epam.lab.Tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Savepoint;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.CourseDAO;
import com.epam.project.db.dao.GroupDAO;
import com.epam.project.db.model.Course;


public class CourseTest {
	
	Connection connection;
	Savepoint savepoint1;
	Course course;
	
	@Before
	public void setUp() throws Exception {
		connection =  DBConnection.getConnection();
		connection.setAutoCommit(false);
		savepoint1 = connection.setSavepoint("Savepoint1");	
		
		course = new Course();
		course.setName("name");
		course.setIcon("icon");
		course.setDescription("description");
	}

	@After
	public void tearDown() throws Exception {
		connection.rollback(savepoint1);
	    connection.close();
	}

	@Test
	public void testAddCourse() throws Exception   {		
			
		Integer id = CourseDAO.addCourse(course, connection);	
		assertNotNull(id);	
	} 	
	
	@Test
	public void testUpdateCourse() throws Exception   {
		
		course.setId(CourseDAO.addCourse(course, connection));
		course.setName("newName");		
	    CourseDAO.updateCourse(course, connection);	
	    Course newCourse = new Course();
	    newCourse = CourseDAO.getCourse(course.getId(), connection);
		assertEquals("newName", newCourse.getName());	
	}
	
	@Test
	public void testGetAllActiveCourses() {		
		
		List<Course> courses = CourseDAO.getAllActiveCourses(connection);
		assertTrue(courses.size() > 0);
	}
	
	@Test
	public void testDeleteCourse() throws Exception   {		
		
		
		Integer id = CourseDAO.addCourse(course, connection);	
		
		GroupDAO.completelyRemove(id, connection);
		CourseDAO.delCourse(id,connection);
		Course newCourse = new Course();
		newCourse = CourseDAO.getCourse(id,connection);
		assertNull(newCourse);
	}
	
}
