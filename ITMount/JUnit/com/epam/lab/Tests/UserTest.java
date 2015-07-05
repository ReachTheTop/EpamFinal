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
import com.epam.project.db.model.User;
import com.epam.project.db.service.UserService;


public class UserTest {
	
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
	public void testAddNewUser() throws Exception   {		
				
			User user = new User();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//user.setBirtdayString("1993-12-08");
			user.setRole_id(2);
			UserService.addNewUser(user);
			
			User newUser = UserService.getUserWhereEmail("email@com");		
			assertNotNull(newUser);		
	} 
	
//	@Test
//	public void testGetUserWhereEmail() {
//		
//		assertNotNull(UserService.getUserWhereEmail("danielle@mail.ua"));
//	}
	
	@Test
	public void testGetByRole() {		
		
		List<User> users = UserService.getByRole("student");
		assertTrue(users.size() > 0);
	}
	
	@Test
	public void testUpdateUser() {	
		User user = UserService.getUserWhereEmail("email@com");	
		
		user.setName("NewName");
		UserService.updateUser(user);
		
		User newUser = new User();
		newUser = UserService.getUserWhereEmail("email@com");	
		
		assertEquals("NewName", newUser.getName());		
	}
	
	@Test
	public void testDeletedUser() {	
		
		User user = UserService.getUserWhereEmail("email@com");
		
		Integer id = user.getId();		
		
		UserService.delUser(id);
		assertNull(UserService.getUserWhereEmail("email@com"));		
	}
}
