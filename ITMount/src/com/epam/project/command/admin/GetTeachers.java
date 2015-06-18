package com.epam.project.command.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.User;
import com.epam.project.db.service.UserService;
import com.google.gson.Gson;

public class GetTeachers implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Gson json = new Gson();
		List<User> teachers = UserService.getByRole("lecturer");
		response.setContentType("application/json");
		response.getWriter().write(json.toJson(teachers));
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "teachers";
	}

}
