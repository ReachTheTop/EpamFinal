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

		// User user = (User) request.getSession().getAttribute("user");
		User user = null;

		user = (User) request.getSession().getAttribute("user");

		Integer course_id = Integer.parseInt(request.getParameter("course_id"));
		

		if (user.getCurriculum_vitae() != null) {
			GroupService.addUserToGroup(user, course_id);
			request.setAttribute("message",
					"You have been registered on cource");
			request.getRequestDispatcher("/WEB-INF/courses/registration.jsp")
					.forward(request, response);
			return;
		} else {
			// REDIRECT TO USER EDIT
		}
	}

	@Override
	public String getName() {

		return "register";
	}

}
