package com.epam.project.db.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.project.db.model.Task;
import com.epam.project.db.model.User;

public class TaskTransformer {

	public static Task getTask(ResultSet rs) {

		Task task = null;

		try {
			while (rs.next()) {
				task = new Task();
				task.setId(rs.getInt(1));
				task.setName(rs.getString(2));
				task.setDescription(rs.getString(3));
				task.setDeadline(rs.getTimestamp(4));
				task.setAvailable(rs.getBoolean(5));
				task.setFile(rs.getString(6));
				task.setIs_active(rs.getBoolean(7));
				task.setGroupID(rs.getInt(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return task;
	}
	
	public static List<Task> getAllTasks(ResultSet rs) {
		List<Task> list = new ArrayList<Task>();
		Task task = null;

		try {
			
			while (rs.next()) {
				task = new Task();
				task.setId(rs.getInt(1));
				task.setName(rs.getString(2));
				task.setDescription(rs.getString(3));
				task.setDeadline(rs.getTimestamp(4));
				task.setAvailable(rs.getBoolean(5));
				task.setFile(rs.getString(6));
				task.setIs_active(rs.getBoolean(7));
				task.setGroupID(rs.getInt(8));
			
				list.add(task);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
}
