package com.epam.project.controller.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Course;
import com.epam.project.db.service.CourseService;

public class CreateCommand implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Course course = new Course();
		course.setName(request.getParameter("name"));
		course.setDescription(request.getParameter("description"));

		course.setIcon(request.getParameter("icon"));

		if (course.isValid()) {
			CourseService.addCourse(course);
			request.getRequestDispatcher(
					"/CourseServlet?action=show&course_id=" + course.getId())
					.forward(request, response);
			return;
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
