package com.epam.project.controller.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Group;
import com.epam.project.db.service.GroupService;

public class UpdateGroup implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer group_id = Integer.parseInt(request.getParameter("group_id"));
		Group group = GroupService.getById(group_id);
		group.setName(request.getParameter("name"));
		if (request.getParameter("teacher_id") != null) {
			group.setTeacher_id(Integer.parseInt(request
					.getParameter("teacher_id")));
		}
		GroupService.updateGroup(group);

		response.setContentType("application/json");
		/*
		 * request.getRequestDispatcher( "/GroupServlet?action=show&group_id=" +
		 * group_id).forward( request, response);
		 */
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "update";
	}

}
