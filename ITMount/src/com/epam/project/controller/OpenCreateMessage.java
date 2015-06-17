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

public class OpenCreateMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OpenCreateMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		List<User> listUsers = AllUserSendMessageTo.getAllUserSendMessageTo(request);
		
	
		request.getRequestDispatcher("WEB-INF/page/sendMessage.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
