package com.epam.project.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Course;
import com.epam.project.db.service.CourseService;
import com.google.gson.Gson;

public class GetCourse implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer course_id = Integer.parseInt(request.getParameter("course_id"));
		Course course = CourseService.getCourse(course_id);
		Gson json = new Gson();
		response.setContentType("application/json");
		response.getWriter().write(json.toJson(course));
	}

	@Override
	public String getName() {

		return "getCourse";
	}

}
