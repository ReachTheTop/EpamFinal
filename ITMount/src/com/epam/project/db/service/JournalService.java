package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.JournalDAO;
import com.epam.project.db.model.Journal;

public class JournalService {
	public static void addJournal(Journal journal){
		Connection connection = DBConnection.getConnection();
		JournalDAO.addNewJournal(journal, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
