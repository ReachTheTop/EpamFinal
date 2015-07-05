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
import com.epam.project.db.model.GroupExamModel;
import com.epam.project.db.service.GroupExamService;

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
			
		GroupExamService.createExam(exam);	
		GroupExamModel newExam = new GroupExamModel();
		newExam = GroupExamService.getByDescription(exam.getDescription());	
		assertNotNull(newExam);	
	} 	
	
	@Test
	public void testUpdateExam() throws Exception   {
		
		GroupExamService.createExam(exam);
		exam.setId(GroupExamService.getByDescription(exam.getDescription()).getId());
		exam.setDescription("newDescription");
		GroupExamService.updateExam(exam);
		
		GroupExamModel newExam = new GroupExamModel();
		newExam = GroupExamService.getById(exam.getId());
		assertEquals("newDescription", newExam.getDescription());	;	
	}
	
	@Test
	public void testGetExams() {		
		
		List<GroupExamModel> exams = GroupExamService.getAll(32);
		assertTrue(exams.size() > 0);
	}
	
	@Test
	public void testDeleteteExam() throws Exception   {		
		
		GroupExamService.createExam(exam);
		exam.setId(GroupExamService.getByDescription(exam.getDescription()).getId());
		GroupExamService.deleteExam(exam.getId());
		assertNull(GroupExamService.getById(exam.getId()));
	}
	
}
