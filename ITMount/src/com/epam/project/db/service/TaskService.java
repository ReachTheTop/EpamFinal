package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.TaskDAO;
import com.epam.project.db.dao.UserDAO;
import com.epam.project.db.model.Task;
import com.epam.project.db.model.User;

public class TaskService {

	public static Task getTask(Integer id) {

		Connection connection = DBConnection.getConnection();
		Task task = TaskDAO.getTask(id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return task;

	}
	
	public static List<Task> getAllTasks() {
		
		Connection connection =  DBConnection.getConnection();
		List<Task> list = TaskDAO.getAllTasks(connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static void addNewTask(Task task){
		
		Connection connection =  DBConnection.getConnection();
		TaskDAO.addNewTask(task, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateTask(Task task){
		
		Connection connection =  DBConnection.getConnection();
		TaskDAO.updateTask(task, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
