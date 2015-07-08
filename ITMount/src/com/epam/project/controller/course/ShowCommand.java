package com.epam.project.controller.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Course;
import com.epam.project.db.service.CourseService;

public class ShowCommand implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Integer course_id = null;
		try {
			if (request.getParameter("course_id") != null) {

				course_id = Integer.parseInt(request.getParameter("course_id"));

				Course course = CourseService.getCourse(course_id);
				
				if(course == null){
					response.sendError(404);
					return;
				}
				request.setAttribute("course", course);
				request.getRequestDispatcher("/WEB-INF/courses/show.jsp")
						.forward(request, response);
				
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
