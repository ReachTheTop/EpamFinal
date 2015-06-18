package com.epam.project.controller.event;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Event;
import com.epam.project.db.service.EventService;

public class ShowEvent implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("I'm in ShowEvent");
		Integer event_id = null;
		try {
			if (request.getParameter("event_id") != null) {

				event_id = Integer.parseInt(request.getParameter("event_id"));

				Event event = EventService.getById(event_id);
				
				if(event == null){
					response.sendError(404);
					return;
				}
				request.setAttribute("event", event);
				System.out.println(event);
				request.getRequestDispatcher("/WEB-INF/event/show.jsp")
						.forward(request, response);
				return;
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
