package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.model.Journal;

public class JournalDAO {
	private static final String SQL_ADD_JOURNAL = "Insert into journal (group_id,user_id,date,visit,description)  value(?,?,?,?,?)";
	private static final String SQL_EDIT_JOURNAL = "UPDATE journal SET visit=? WHERE id=?;";
	public static void updateVisit (Boolean visit, Integer id){
		PreparedStatement stmt;

		Connection con = DBConnection.getConnection();

		try {
			stmt = con.prepareStatement(SQL_EDIT_JOURNAL);
			
			stmt.setBoolean(1, visit);
			stmt.setInt(2, id);

			stmt.executeUpdate();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	public static void addNewJournal(Journal journal, Connection connection) {

		PreparedStatement stmt;

		Connection con = DBConnection.getConnection();

		try {
			stmt = con.prepareStatement(SQL_ADD_JOURNAL);
			
			stmt.setInt(1, journal.getGroupID());
			stmt.setInt(2, journal.getUserID());
			stmt.setTimestamp(3, new Timestamp(journal.getDate().getTime()));
			stmt.setBoolean(4, journal.getVisit());
			stmt.setString(5, journal.getDescription());

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}
