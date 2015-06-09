package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.model.Task;
import com.epam.project.db.model.User;
import com.epam.project.db.transformer.TaskTransformer;
import com.epam.project.db.transformer.UserTransformer;

public class TaskDAO {

	public static final String SQL_UPDATE_TASK = "Update task SET name=?, description=?, deadline=?, available=?,"
			+ " file=?, is_active =?, group_id = ? WHERE id=?";

	public static final String SQL_ADD_NEW_TASK = "Insert into task (name,description,deadline,available,file,is_active,group_id)  value(?,?,?,?,?,?,?)";

	public static final String SQL_GET_ALL_TASKS = "SELECT * FROM task";
	public static final String SQL_GET_TASK = "SELECT * FROM task WHERE id=?";

	public static Task getTask(Integer id, Connection connection) {

		ResultSet rs = null;
		Task task = null;
		try {

			PreparedStatement st = connection.prepareStatement(SQL_GET_TASK);
			st.setInt(1, id);
			rs = st.executeQuery();
			task = TaskTransformer.getTask(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return task;
	}

	public static List<Task> getAllTasks(Connection connection) {

		ResultSet rs = null;
		List<Task> list = null;
		try {

			PreparedStatement st = connection
					.prepareStatement(SQL_GET_ALL_TASKS);
			rs = st.executeQuery();
			list = TaskTransformer.getAllTasks(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void addNewTask(Task task, Connection connection) {

		PreparedStatement stmt;

		Connection con = DBConnection.getConnection();

		try {
			stmt = con.prepareStatement(SQL_ADD_NEW_TASK);
			
			stmt.setString(1, task.getName());
			stmt.setString(2, task.getDescription());
			stmt.setTimestamp(3, new Timestamp(task.getDeadline().getTime()));
			stmt.setBoolean(4, task.getAvailable());
			stmt.setString(5, task.getFile());
			stmt.setBoolean(6, task.getIs_active());
			stmt.setInt(7, task.getGroupID());

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	public static void updateTask(Task task, Connection connection) {

		try {

			PreparedStatement stmt = connection
					.prepareStatement(SQL_UPDATE_TASK);

			stmt.setString(1, task.getName());
			stmt.setString(2, task.getDescription());
			stmt.setTimestamp(3, new Timestamp(task.getDeadline().getTime()));
			stmt.setBoolean(4, task.getAvailable());
			stmt.setString(5, task.getFile());
			stmt.setBoolean(6, task.getIs_active());
			stmt.setInt(7, task.getGroupID());

			stmt.setInt(8, task.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
