package com.epam.project.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.dao.GroupCorteg;
import com.epam.project.db.service.GroupService;
import com.google.gson.Gson;

public class GroupAdministration implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Gson json = new Gson();
		String token = request.getParameter("search");
		String pageParam = request.getParameter("page");
		Integer page = null;
		if (token == null) {
			token = "";
		}
		if (pageParam == null) {
			page = 0;
		} else {
			page = Integer.parseInt(pageParam);
		}

		GroupCorteg groups = GroupService.getAll(token, page);

		response.setContentType("application/json");

		response.getWriter().write(json.toJson(groups));
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "groups";
	}

}
