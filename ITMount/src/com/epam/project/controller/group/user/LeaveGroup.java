package com.epam.project.controller.group.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.service.GroupUserService;

public class LeaveGroup implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer user_id = Integer.parseInt( request.getParameter("user_id"));
		Integer group_id = Integer.parseInt(request.getParameter("group_id"));
		GroupUserService.leaveGroup(group_id, user_id);
		response.sendRedirect("/ITMount/UserServlet");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "leaveGroup";
	}

}
