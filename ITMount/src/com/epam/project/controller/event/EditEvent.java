package com.epam.project.controller.event;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Event;
import com.epam.project.db.service.EventService;

public class EditEvent implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("event_id") != null) {

			Integer event_id = Integer.parseInt(request
					.getParameter("event_id"));
			
			Event event = EventService.getById(event_id);
			request.setAttribute("event", event);
			request.getRequestDispatcher("/WEB-INF/event/edit.jsp").forward(
					request, response);
			return;
		} else {
			response.sendError(404);
			return;
		}

	}

	@Override
	public String getName() {

		return "edit";
	}

}
