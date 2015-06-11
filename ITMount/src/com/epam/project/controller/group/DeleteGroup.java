package com.epam.project.controller.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.service.GroupService;

public class DeleteGroup implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer id = Integer.parseInt(request.getParameter("group_id"));
		GroupService.deleteGroup(id);
		response.sendRedirect(request.getHeader("Referer"));
		return;

	}

	@Override
	public String getName() {

		return "delete";
	}

}
