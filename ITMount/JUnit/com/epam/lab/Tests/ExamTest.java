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
import com.epam.project.db.dao.GroupExamDAO;
import com.epam.project.db.model.GroupExamModel;

public class ExamTest {
	
	Connection connection;
	Savepoint savepoint1;
	GroupExamModel exam;
	
	@Before
	public void setUp() throws Exception {
		connection =  DBConnection.getConnection();
		connection.setAutoCommit(false);
		savepoint1 = connection.setSavepoint("Savepoint1");	
		
		exam = new GroupExamModel();
		exam.setDescription("description");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = sdf.parse("2015-12-08");
			exam.setExam_date(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		exam.setGroup_id(32);
	}

	@After
	public void tearDown() throws Exception {
		connection.rollback(savepoint1);
	    connection.close();
	}

	@Test
	public void testAddExam() throws Exception   {		
			
		GroupExamDAO.createExam(connection, exam);	
		GroupExamModel newExam = new GroupExamModel();
		newExam = GroupExamDAO.getByDescription(connection, exam.getDescription());	
		assertNotNull(newExam);	
	} 	
	
	@Test
	public void testUpdateExam() throws Exception   {
		
		GroupExamDAO.createExam(connection, exam);
		exam.setId(GroupExamDAO.getByDescription(connection, exam.getDescription()).getId());
		exam.setDescription("newDescription");
		GroupExamDAO.updateExam(connection, exam);
		
		GroupExamModel newExam = new GroupExamModel();
		newExam = GroupExamDAO.getById(connection, exam.getId());
		assertEquals("newDescription", newExam.getDescription());	;	
	}
	
	@Test
	public void testGetExams() {		
		
		List<GroupExamModel> exams = GroupExamDAO.getAll(connection, 32);
		assertTrue(exams.size() > 0);
	}
	
	@Test
	public void testDeleteteExam() throws Exception   {		
		
		GroupExamDAO.createExam(connection, exam);
		exam.setId(GroupExamDAO.getByDescription(connection, exam.getDescription()).getId());
		GroupExamDAO.deleteExam(connection, exam.getId());
		assertNull(GroupExamDAO.getById(connection, exam.getId()));
	}
	
}
