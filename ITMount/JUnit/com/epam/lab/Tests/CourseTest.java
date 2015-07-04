package com.epam.lab.Tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Savepoint;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.model.Course;
import com.epam.project.db.service.CourseService;


public class CourseTest {
	
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
				
		Course course = new Course();
		course.setName("name");
		course.setIcon("icon");
		course.setDescription("description");
		
		Integer id = CourseService.addCourse(course);	
		assertNotNull(id);	
		//CourseService.delCourse(id);
	} 	
	
	@Test
	public void testGetAllActiveCourses() {		
		
		List<Course> courses = CourseService.getAllActiveCourses();
		assertTrue(courses.size() > 0);
	}
	
//	@Test
//	public void testDeleteteCourse() throws Exception   {		
//				
//		Course course = new Course();
//		course.setName("name");
//		course.setIcon("icon");
//		course.setDescription("description");
//		
//		Integer id = CourseService.addCourse(course);	
//		
//		
//		
//		CourseService.delCourse(id);
//		//assertNull(id);
//	}
	
}
