package com.epam.project.controller.event;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Event;
import com.epam.project.db.service.EventService;

public class IndexEvent implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Event> events = EventService.getAll();
		//List<Course> courses = CourseService.getAllCourses();
		request.setAttribute("events", events);
		request.getRequestDispatcher("/WEB-INF/event/index.jsp").forward(
				request, response);
	}

	@Override
	public String getName() {

		return "index";
	}

}
