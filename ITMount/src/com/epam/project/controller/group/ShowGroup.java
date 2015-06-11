package com.epam.project.controller.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Course;
import com.epam.project.db.model.Group;
import com.epam.project.db.service.CourseService;
import com.epam.project.db.service.GroupService;

public class ShowGroup implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer group_id = null;
		try {
			if (request.getParameter("group_id") != null) {

				group_id = Integer.parseInt(request.getParameter("group_id"));

				Group group = GroupService.getById(group_id);

				if (group == null) {
					response.sendError(404);
					return;
				}
				request.setAttribute("group", group);
				request.getRequestDispatcher("/WEB-INF/group/show.jsp")
						.forward(request, response);
				return;
			}
		} catch (NumberFormatException e) {
			response.sendError(404);
		}

	}

	@Override
	public String getName() {

		return "show";
	}

}
