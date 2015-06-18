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

		request.setAttribute("id_group", 11);
	
		List<Task> listTask = TaskService.getAllTasksByGroupId(11);
		
		request.setAttribute("listTask",listTask );
		
		request.getRequestDispatcher("WEB-INF/task/newTask.jsp").forward(
				request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("post");

	}

}

