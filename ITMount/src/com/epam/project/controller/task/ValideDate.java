package com.epam.project.controller.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.epam.project.db.model.Task;
import com.epam.project.db.service.TaskService;

public class ValideDate {

	public static List<Task> getTasksAfterValideDate(int groupId) {

		List<Task> tasks = TaskService.getAllTasksByGroupId(groupId);

		Date currentDate = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

		for (Task task : tasks) {

			if (compareDatesByDateMethods(format, task.getDeadline(),
					currentDate) == false) {
				task.setAvailable(false);
				TaskService.updateTask(task);
				
			}

		}

		return tasks;

	}

	public static boolean compareDatesByDateMethods(DateFormat df,
			Date deadline, Date currentDate) {

		if (deadline.before(currentDate)) {
			return false;
		}
		return true;

	}

}
