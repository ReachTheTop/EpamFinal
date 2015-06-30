package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.model.UserAttendance;
import com.epam.project.db.transformer.UserAttendanceTransformer;

public class UserAttendanceDAO {
	private static final String SQL_GET_USERS_ATTENDANCE_BY_GROUP_ID = "SELECT user.id, user.name, user.midle_name,user.surname,user.birthday,user.role_id,user.password_hash,user.curriculum_vitae,user.description,user.is_active,is_confirmed,user.key1, user.image,user.email, journal.id, journal.date,journal.visit,journal.description from user INNER JOIN journal on user.id = journal.user_id where journal.group_id = ? ORDER BY user.id ASC";
	
	public static List<UserAttendance> getGroupAttendance(Integer group_id, Connection connection){
		ResultSet rs = null;
		List<UserAttendance> list = null;
		try {
			PreparedStatement st = connection.prepareStatement(SQL_GET_USERS_ATTENDANCE_BY_GROUP_ID);
			st.setInt(1, group_id );
			rs = st.executeQuery();
			list = UserAttendanceTransformer.getAllRecords(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
