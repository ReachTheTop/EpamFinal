package com.epam.project.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.service.UserService;

public class ChangeRole implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer user_id = Integer.parseInt(request.getParameter("user_id"));
		Integer role_id = Integer.parseInt(request.getParameter("role_id"));
		UserService.changeRole(user_id, role_id);
		
		response.setContentType("application/json");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "changeRole";
	}

}
