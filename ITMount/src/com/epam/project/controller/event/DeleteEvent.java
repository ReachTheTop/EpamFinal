package com.epam.project.controller.event;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Event;
import com.epam.project.db.service.EventService;

public class DeleteEvent implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer event_id = Integer.parseInt(request.getParameter("event_id"));
				
		EventService.delete(event_id);
		response.sendRedirect(request.getHeader("Referer"));
		return;
	}

	@Override
	public String getName() {
		return "delete";
	}

}
