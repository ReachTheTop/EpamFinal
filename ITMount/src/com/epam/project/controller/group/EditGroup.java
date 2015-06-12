package com.epam.project.controller.group;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Group;
import com.epam.project.db.model.User;
import com.epam.project.db.service.GroupService;
import com.epam.project.db.service.UserService;

public class EditGroup implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("group_id") != null) {
			Integer group_id = Integer.parseInt(request
					.getParameter("group_id"));
			Group group = GroupService.getById(group_id);
			List<User> teachers = UserService.getByRole("lecturer");
			request.setAttribute("teachers", teachers);
			request.setAttribute("group", group);
			request.getRequestDispatcher("/WEB-INF/group/edit.jsp").forward(
					request, response);
			return;
		} else {
			response.sendError(404);
		}

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "edit";
	}

}
