package com.epam.project.controller.course;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Course;
import com.epam.project.db.service.CourseService;

public class IndexCommand implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {



		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		List<Course> courses = CourseService.getAllActiveCourses();

		request.setAttribute("courses", courses);
		request.getRequestDispatcher("/WEB-INF/courses/index.jsp").forward(
				request, response);
	}

	@Override
	public String getName() {

		return "index";
	}

}
