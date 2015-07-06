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
import com.epam.project.db.dao.TaskDAO;
import com.epam.project.db.model.Task;

public class TaskTest {

	Connection connection;
	Savepoint savepoint1;
	Task task;
	
	@Before
	public void setUp() throws Exception {
		connection =  DBConnection.getConnection();
		connection.setAutoCommit(false);
		savepoint1 = connection.setSavepoint("Savepoint1");	
		
		task = new Task();
		task.setName("name");
		task.setDescription("description");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = sdf.parse("2015-12-08");
			task.setDeadline(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		task.setAvailable(true);
		task.setFile("file");
		task.setIs_active(true);
		task.setGroupID(32);
	}

	@After
	public void tearDown() throws Exception {
		connection.rollback(savepoint1);
	    connection.close();
	}

	@Test
	public void testAddMessage() throws Exception   {		
		
		TaskDAO.addNewTask(task, connection);
		task.setId(TaskDAO.getTaskByName(task.getName(), connection).getId());
		assertNotNull(task.getId());	
	} 	
	
	@Test
	public void testUpdateMessage() throws Exception   {
		
		TaskDAO.addNewTask(task, connection);
		task.setId(TaskDAO.getTaskByName(task.getName(), connection).getId());
		task.setDescription("newDescription");
		TaskDAO.updateTask(task, connection);	
		Task newTask = new Task();
		newTask = TaskDAO.getTask(task.getId(), connection);
		assertEquals("newDescription", newTask.getDescription());	
	}
	
	@Test
	public void testGetAllMessage() {		
		
		List<Task> tasks = TaskDAO.getAllTasks(connection);
		assertTrue(tasks.size() > 0);
	}
	
	@Test
	public void testDeleteMessage() throws Exception   {		
		
		
		TaskDAO.addNewTask(task, connection);
		task.setId(TaskDAO.getTaskByName(task.getName(), connection).getId());
		System.out.println("ID = " + task.getId());
		TaskDAO.delTask(task.getId(),connection);
		Task newTask = new Task();
		newTask = TaskDAO.getTask(task.getId(), connection);
		assertNull(newTask);
	}
	
}