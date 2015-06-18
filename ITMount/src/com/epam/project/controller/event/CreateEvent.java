package com.epam.project.controller.event;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Event;
import com.epam.project.db.service.EventService;

public class CreateEvent implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		Event event = new Event();
		event.setDescription(request.getParameter("description"));
		try {
			event.setDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(request.getParameter("date")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		event.setGroup_id(Integer.parseInt(request.getParameter("group_id")));


		if (event.isValid()) {
	
			EventService.newEvent(event);
	
			request.getRequestDispatcher(
					"/EventServlet?action=index").forward(request, response);
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
