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
import com.epam.project.db.dao.UserDAO;
import com.epam.project.db.model.User;


public class UserTest {
	
	Connection connection;
	Savepoint savepoint1;
	User user;
	
	@Before
	public void setUp() throws Exception {
		connection =  DBConnection.getConnection();
		connection.setAutoCommit(false);
		savepoint1 = connection.setSavepoint("Savepoint1");
		
		user = new User(); ;
		user.setName("name");
		user.setMiddle_name("midlename");
		user.setSurname("surname");
		user.setEmail("email@com");
		user.setPassword_hash("password");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = sdf.parse("1993-12-08");
			user.setBirtday(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user.setRole_id(2);
	}

	@After
	public void tearDown() throws Exception {
		connection.rollback(savepoint1);
	    connection.close();
	}

	@Test
	public void testAddNewUser() throws Exception   {		
				
			
			UserDAO.addNewUser(user, connection);
					
			assertNotNull(UserDAO.getUserWhereEmail(user.getEmail(), connection));		
	} 
	
	
	@Test
	public void testGetByRole() {		
		
		List<User> users = UserDAO.getByRole("student", connection);
		assertTrue(users.size() > 0);
	}
	
	@Test
	public void testUpdateUser() {	
		
		UserDAO.addNewUser(user, connection);
		user = UserDAO.getUserWhereEmail(user.getEmail(), connection);
		//user.setId(UserDAO.getUserWhereEmail(user.getEmail(), connection).getId());
		
		user.setName("NewName");
		UserDAO.updateUser(user, connection);
		
		User newUser = new User();
		newUser = UserDAO.getUserWhereEmail(user.getEmail(), connection);	
		
		assertEquals("NewName", newUser.getName());		
	}
		
	@Test
	public void testDeletedUser() {	
		
		UserDAO.addNewUser(user, connection);
		user.setId(UserDAO.getUserWhereEmail(user.getEmail(), connection).getId());		
		
		UserDAO.delUser(user.getId(), connection);
		assertNull(UserDAO.getUserWhereEmail(user.getEmail(), connection));		
	}
}
