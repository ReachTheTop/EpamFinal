package com.epam.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.project.controller.groupuser.AllUserSendMessageTo;
import com.epam.project.db.model.Group;
import com.epam.project.db.model.User;
import com.epam.project.db.service.GroupService;
import com.epam.project.db.service.GroupUserService;

public class OpenCreateTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OpenCreateTask() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("id_group", 11);
		System.out.println("get");
		request.getRequestDispatcher("WEB-INF/task/newTask.jsp").forward(
				request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("post");

	}

}
