package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.HomeworkDAO;
import com.epam.project.db.model.HomeWork;

public class HomeWorkService {

	public static void addHomeWork(HomeWork HomeWork) {
		Connection connection =  DBConnection.getConnection();
		HomeworkDAO.addHomework(HomeWork, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void delHomeWork(Integer id) {
		Connection connection =  DBConnection.getConnection();
		HomeworkDAO.delHomework(id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateHomeWork(HomeWork HomeWork) {
		Connection connection =  DBConnection.getConnection();
		HomeworkDAO.updateHomework(HomeWork, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<HomeWork> getAllHomeWorks() {
		Connection connection =  DBConnection.getConnection();
		List<HomeWork> list = HomeworkDAO.getAllHomework(connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static HomeWork getHomeWork(Integer id) {
		Connection connection =  DBConnection.getConnection();
		HomeWork homeWork = HomeworkDAO.getHomework(id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return homeWork;
	}
	
}
