package com.epam.project.controller.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Course;
import com.epam.project.db.service.CourseService;

public class TrigerCourse implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer course_id = Integer.parseInt(request.getParameter("course_id"));
		Course course = new Course();
		course.setId(course_id);
		course.setStatus(request.getParameter("status"));
		CourseService.trigerCourse(course);
		response.sendRedirect(request.getHeader("Referer"));
		return;
	}

	@Override
	public String getName() {
		return "triger";
	}

}
