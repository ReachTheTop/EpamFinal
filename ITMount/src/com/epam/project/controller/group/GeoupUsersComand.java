package com.epam.project.controller.group;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.User;
import com.epam.project.db.service.UserService;
import com.google.gson.Gson;

public class GeoupUsersComand implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String token = request.getParameter("token");
		List<User> users = UserService.getUserByToken(token);
		
		Gson json = new Gson();

		response.setContentType("application/json");
		response.getWriter().write(json.toJson(users));
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "users";
	}

}
