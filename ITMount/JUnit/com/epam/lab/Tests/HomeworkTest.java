package com.epam.lab.Tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Savepoint;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.HomeworkDAO;
import com.epam.project.db.dao.LanguageDAO;
import com.epam.project.db.model.HomeWork;
import com.epam.project.db.model.Language;


public class HomeworkTest {
	
	Connection connection;
	Savepoint savepoint1;
	HomeWork homework;
	
	
	@Before
	public void setUp() throws Exception {
		connection =  DBConnection.getConnection();
		connection.setAutoCommit(false);
		savepoint1 = connection.setSavepoint("Savepoint1");	
		
		homework = new HomeWork();
		homework.setData("data");
		homework.setTask_id(1);
		homework.setUser_id(11);
		homework.setRating(100);
		
	}

	@After
	public void tearDown() throws Exception {
		connection.rollback(savepoint1);
	    connection.close();
	}

	@Test
	public void testAddHomework() throws Exception   {		
			
		HomeworkDAO.addHomework(homework, connection);	
		homework = HomeworkDAO.getHomeworkWhereUserTask(homework.getUser_id(), homework.getTask_id(), connection);
		assertNotNull(homework);	
	} 	
	
	@Test
	public void testUpdateHomework() throws Exception   {
		
		HomeworkDAO.addHomework(homework, connection);	
		homework = HomeworkDAO.getHomeworkWhereUserTask(homework.getUser_id(), homework.getTask_id(), connection);
		homework.setData("newData");		
		HomeworkDAO.updateHomework(homework, connection);	
		HomeWork newHomework = new HomeWork();
		newHomework = HomeworkDAO.getHomework(homework.getId(), connection);
		assertEquals("newData", newHomework.getData());	
	}
	
	@Test
	public void testGetAllHomeworks() {		
		
		List<HomeWork> homeworks = HomeworkDAO.getAllHomework(connection);
		assertTrue(homeworks.size() > 0);
	}
	
	@Test
	public void testDeleteteHomework() throws Exception   {		
		
		
		HomeworkDAO.addHomework(homework, connection);	
		homework = HomeworkDAO.getHomeworkWhereUserTask(homework.getUser_id(), homework.getTask_id(), connection);
		
		
		HomeworkDAO.delHomework(homework.getId(),connection);
		HomeWork newHomework = new HomeWork();
		newHomework = HomeworkDAO.getHomework(homework.getId(),connection);
		assertNull(newHomework);
	}
	
}
