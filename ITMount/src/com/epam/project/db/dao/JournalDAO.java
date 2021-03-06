package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.model.DayVisit;
import com.epam.project.db.model.Journal;
import com.epam.project.db.model.Task;
import com.epam.project.db.model.UserVisiting;
import com.epam.project.db.transformer.JournalTransformer;

public class JournalDAO {

	public static final String SQL_UPDATE_JOURNAL = "Update journal SET group_id=?, user_id=?, date=?, visit=?,"
			+ " description=? WHERE id=?";

	public static final String SQL_ADD_NEW_JOURNAL = "Insert into journal  (group_id,user_id,date,visit,description)  value(?,?,?,?,?)";

	public static final String SQL_GET_ALL_JOURNAL = "SELECT * FROM journal";
	public static final String SQL_GET_JOURNAL = "SELECT * FROM journal WHERE id=?";
	
	public static final String SQL_GET_JOURNAL_USER ="select* from journal where group_id = ? and user_id = ? order by date desc";
	
	public static final String SQL_GET_ALL_JOURNAL_BY_DATE_AND_GROUP = "SELECT * FROM journal where group_id = ? and date = ?";
	
	public static final String SQL_GET_JOURNAL_BY_USER_AND_DATE = "SELECT * FROM journal WHERE user_id = ? and date = ?;";
	public static final String DELETE_JOURNAL = "DELETE FROM journal WHERE id = ?;";

	public static List<Journal> getListJournalGroup(Integer idGroup,
			String dateLesson, Connection connection) {
		
		ResultSet rs = null;
		List<Journal> listJournal = null;
		try {

			PreparedStatement st = connection.prepareStatement(SQL_GET_ALL_JOURNAL_BY_DATE_AND_GROUP);
			st.setInt(1, idGroup);
			st.setString(2, dateLesson);
			
			rs = st.executeQuery();
			listJournal = JournalTransformer.getListJournal(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listJournal;
		
		
	}
	
	
	
	public static List<DayVisit> getUserVisitingGroup(Integer idGroup, Integer idUser, Connection connection){
	
		ResultSet rs = null;
		List<DayVisit> journal = null;
		try {

			PreparedStatement st = connection.prepareStatement(SQL_GET_JOURNAL_USER);
			st.setInt(1, idGroup);
			st.setInt(2, idUser);
			rs = st.executeQuery();
			journal = JournalTransformer.getJournalDayUser(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return journal;
		
		
		
	}
	
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
		

		try {
			stmt = connection.prepareStatement(SQL_ADD_NEW_JOURNAL);
			
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
	
	public static Journal getJournalByUserAndDate(Integer user_id, Date date, Connection connection) {

		ResultSet rs = null;
		Journal journal = null;
		try {

			PreparedStatement st = connection.prepareStatement(SQL_GET_JOURNAL_BY_USER_AND_DATE);
			st.setInt(1, user_id);
			st.setTimestamp(2, new Timestamp(date.getTime()));
			rs = st.executeQuery();
			journal = JournalTransformer.getJournal(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return journal;
	}
	
	public static void delJournal(Integer id, Connection connection) {

		try {

			PreparedStatement st = connection.prepareStatement(DELETE_JOURNAL);
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
