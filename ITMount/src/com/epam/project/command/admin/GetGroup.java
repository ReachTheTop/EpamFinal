package com.epam.project.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Group;
import com.epam.project.db.service.GroupService;
import com.google.gson.Gson;

public class GetGroup implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer group_id = Integer.parseInt(request.getParameter("group_id"));
		Group group = GroupService.getById(group_id);
		Gson json = new Gson();
		response.setContentType("application/json");
		
		response.getWriter().write(json.toJson(group));

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "getGroup";
	}

}
