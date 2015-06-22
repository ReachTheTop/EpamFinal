package com.epam.project.controller.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.User;
import com.epam.project.db.service.GroupService;

public class CourseRegistration implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = null;

		user = (User) request.getSession().getAttribute("user");

		Integer course_id = Integer.parseInt(request.getParameter("course_id"));

		Integer group_id = null;

		System.out.println(user.getCurriculum_vitae());

		if (user != null && !user.getCurriculum_vitae().isEmpty()) {
			group_id = GroupService.addUserToGroup(user, course_id);
			request.getRequestDispatcher(
					"/GroupServlet?action=show&group_id=" + group_id).forward(
					request, response);
			return;
		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;

		}
	}

	@Override
	public String getName() {

		return "register";
	}

}
