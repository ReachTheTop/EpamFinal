package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.model.Journal;
import com.epam.project.db.model.Task;
import com.epam.project.db.transformer.JournalTransformer;

public class JournalDAO {

	public static final String SQL_UPDATE_JOURNAL = "Update journal SET group_id=?, user_id=?, date=?, visit=?,"
			+ " description=? WHERE id=?";

	public static final String SQL_ADD_NEW_JOURNAL = "Insert into journal  (group_id,user_id,date,visit,description)  value(?,?,?,?,?)";

	public static final String SQL_GET_ALL_JOURNAL = "SELECT * FROM journal";
	public static final String SQL_GET_JOURNAL = "SELECT * FROM journal WHERE id=?";
	
	public static Journal getJournal(Integer id, Connection connection) {

		ResultSet rs = null;
		Journal journal = null;
		try {

			PreparedStatement st = connection.prepareStatement(SQL_GET_JOURNAL);
			st.setInt(1, id);
			rs = st.executeQuery();
			journal = JournalTransformer.getJournal(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return journal;
	}
	
	public static List<Journal> getAllJournals(Connection connection) {

		ResultSet rs = null;
		List<Journal> list = null;
		try {

			PreparedStatement st = connection
					.prepareStatement(SQL_GET_ALL_JOURNAL);
			rs = st.executeQuery();
			list = JournalTransformer.getAllJournals(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void addNewJournal(Journal journal, Connection connection) {

		PreparedStatement stmt;
		Connection con = DBConnection.getConnection();

		try {
			stmt = con.prepareStatement(SQL_ADD_NEW_JOURNAL);
			
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
	
	public static void updateJournal(Journal journal, Connection connection) {

		try {

			PreparedStatement stmt = connection
					.prepareStatement(SQL_UPDATE_JOURNAL);

			stmt.setInt(1, journal.getGroupID());
			stmt.setInt(2, journal.getUserID());
			stmt.setTimestamp(3, new Timestamp(journal.getDate().getTime()));
			stmt.setBoolean(4, journal.getVisit());
			stmt.setString(5, journal.getDescription());

			stmt.setInt(6, journal.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
}
