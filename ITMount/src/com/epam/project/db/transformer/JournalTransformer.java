package com.epam.project.db.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.project.db.model.Journal;
import com.epam.project.db.model.Task;

public class JournalTransformer {
	
	public static Journal getJournal(ResultSet rs) {

		Journal journal = null;

		try {
			while (rs.next()) {
				
				journal = new Journal();
				journal.setId(rs.getInt(1));
				journal.setGroupID(rs.getInt(2));
				journal.setUserID(rs.getInt(3));
				journal.setDate(rs.getDate(4));
				journal.setVisit(rs.getBoolean(5));
				journal.setDescription(rs.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return journal;
	}
	
	public static List<Journal> getAllJournals(ResultSet rs) {
		List<Journal> list = new ArrayList<Journal>();
		Journal journal = null;

		try {
			
			while (rs.next()) {
				journal = new Journal();
				journal.setId(rs.getInt(1));
				journal.setGroupID(rs.getInt(2));
				journal.setUserID(rs.getInt(3));
				journal.setDate(rs.getDate(4));
				journal.setVisit(rs.getBoolean(5));
				journal.setDescription(rs.getString(6));
			
				list.add(journal);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

}
