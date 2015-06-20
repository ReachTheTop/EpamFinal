package com.epam.project.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.db.model.Task;
import com.epam.project.db.service.TaskService;

public class OpenCreateTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OpenCreateTask() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		  response.setCharacterEncoding("utf-8");

		int idGroup = Integer.parseInt(request.getParameter("group_id"));
		List<Task> listTask = TaskService.getAllTasksByGroupId(idGroup);
		
		request.setAttribute("listTask", listTask);
		request.setAttribute("group_id", idGroup);
		
		request.getRequestDispatcher("WEB-INF/task/newTask.jsp").forward(
				request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("post");

	}

}
