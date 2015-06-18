package com.epam.project.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.dao.UserCorteg;
import com.epam.project.db.model.User;
import com.epam.project.db.service.ContactService;
import com.epam.project.db.service.UserService;
import com.google.gson.Gson;

public class UserAdministration implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String token = request.getParameter("search");
		Integer page = null;
		if (token == null) {
			token = "";
		}
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			page = 0;
		}

		UserCorteg users = UserService.getAllUsers(token, page);
		for (User user : users.getUsers()) {
			user.setContact(ContactService.getContact(user.getId()));
		}
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
