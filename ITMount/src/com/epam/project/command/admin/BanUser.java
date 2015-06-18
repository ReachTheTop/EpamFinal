package com.epam.project.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.User;
import com.epam.project.db.service.UserService;
import com.google.gson.Gson;

public class BanUser implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer user_id = Integer.parseInt(request.getParameter("user_id"));
		User user= UserService.getUser(user_id);
		user.setIs_active(!user.getIs_active());
		UserService.updateUser(user);
		Gson json = new Gson();
		response.setContentType("application/json");
		response.getWriter().write(json.toJson(user));
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "trigerUser";
	}

}
