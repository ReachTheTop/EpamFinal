package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.UserAttendanceDAO;
import com.epam.project.db.model.UserAttendance;

public class UserAttendanceService {
	public static List<UserAttendance> getUserAttendanceByGroup(Integer group_id) {
		Connection connection =  DBConnection.getConnection();
		List<UserAttendance> list = UserAttendanceDAO.getGroupAttendance(group_id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
