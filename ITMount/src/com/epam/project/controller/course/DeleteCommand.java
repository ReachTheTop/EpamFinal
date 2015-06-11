package com.epam.project.controller.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.service.CourseService;

public class DeleteCommand implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("course_id") != null) {

			Integer id = Integer.parseInt(request.getParameter("course_id"));
			CourseService.delCourse(id);
			response.sendRedirect("/CourseServlet");
		} else {
			response.sendError(404);
		}
	}

	@Override
	public String getName() {

		return "delete";
	}

}
