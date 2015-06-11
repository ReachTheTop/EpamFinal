package com.epam.project.controller.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Group;
import com.epam.project.db.service.GroupService;

public class CreateGroup implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Group group = new Group();
		group.setCourse_id(Integer.parseInt(request.getParameter("course_id")));
		group.setName(request.getParameter("name"));
		group.setTeacher_id(Integer.parseInt(request.getParameter("teacher_id")));

		if (group.isValid()) {

			GroupService.newGroup(group);

			request.getRequestDispatcher(
					"/GroupServlet?action=show&group_id=" + group.getId())
					.forward(request, response);
			;
		} else {
			response.sendRedirect(request.getHeader("Referer"));
			return;
		}

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "create";
	}

}
