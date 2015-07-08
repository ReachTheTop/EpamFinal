package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.JournalDAO;
import com.epam.project.db.dao.TaskDAO;
import com.epam.project.db.model.DayVisit;
import com.epam.project.db.model.Journal;
import com.epam.project.db.model.Task;

public class JournalService {
	
	public static List<DayVisit> getJournalDayVisit(Integer idGroup, Integer idUser) {

		Connection connection = DBConnection.getConnection();
		List<DayVisit> journal = JournalDAO.getUserVisitingGroup(idGroup, idUser, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return journal;

	}

	public static Journal getJournal(Integer id) {

		Connection connection = DBConnection.getConnection();
		Journal journal = JournalDAO.getJournal(id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return journal;

	}

	public static List<Journal> getAllJournals() {

		Connection connection = DBConnection.getConnection();
		List<Journal> list = JournalDAO.getAllJournals(connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static void addNewJournal(Journal journal) {

		Connection connection = DBConnection.getConnection();
		JournalDAO.addNewJournal(journal, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
public static void updateJournal(Journal journal){
		
		Connection connection =  DBConnection.getConnection();
		JournalDAO.updateJournal(journal, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
