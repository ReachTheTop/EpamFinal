package com.epam.project.controller.task;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Task;
import com.epam.project.db.service.TaskService;
import com.google.gson.Gson;

public class ShowTask implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer task_id = Integer.parseInt(request.getParameter("task_id"));
		Task task = TaskService.getTask(task_id);
		Gson json = new Gson();
		
		response.setContentType("application/json");
		response.getWriter().write(json.toJson(task));

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "show";
	}

}
