package com.epam.project.controller.group;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.GroupUserDAO;

public class TEST_CLASS {

	public static void main(String[] args) {
		Connection connection = DBConnection.getConnection();
		List<String> users = new  ArrayList<>();
		users.add("hiken127@gmail.com");
		GroupUserDAO.leaveUsersInGroup(connection, 11,users);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
