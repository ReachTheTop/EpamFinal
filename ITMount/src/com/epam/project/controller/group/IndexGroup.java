package com.epam.project.controller.group;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Group;
import com.epam.project.db.service.GroupService;

public class IndexGroup implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Group> groups = GroupService.getAll();
		request.setAttribute("groups", groups);
		request.getRequestDispatcher("/WEB-INF/group/index.jsp").forward(
				request, response);

	}

	@Override
	public String getName() {

		return "index";
	}

}
