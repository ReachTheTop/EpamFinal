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
import com.epam.project.db.service.GroupService;


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
			
		Integer id = CourseService.addCourse(course);	
		assertNotNull(id);	
	} 	
	
	@Test
	public void testUpdateCourse() throws Exception   {
		
		course.setId(CourseService.addCourse(course));
		course.setName("newName");		
	    CourseService.updateCourse(course);	
	    Course newCourse = new Course();
	    newCourse = CourseService.getCourse(course.getId());
		assertEquals("newName", newCourse.getName());	
	}
	
	@Test
	public void testGetAllActiveCourses() {		
		
		List<Course> courses = CourseService.getAllActiveCourses();
		assertTrue(courses.size() > 0);
	}
	
	@Test
	public void testDeleteteCourse() throws Exception   {		
		
		
		Integer id = CourseService.addCourse(course);	
		
		GroupService.completelyRemove(id);
		CourseService.delCourse(id);
		Course newCourse = new Course();
		newCourse = CourseService.getCourse(id);
		assertNull(newCourse);
	}
	
}
