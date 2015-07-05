package com.epam.project.controller.feedback;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Feedback;
import com.epam.project.db.service.FeedbackService;

public class Reply implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id =Integer.parseInt(request.getParameter("id"));
		Feedback feedback = FeedbackService.getFeedback(id);
		request.setAttribute("subject", "ITmount administration");
		request.setAttribute("feedback", feedback);
		request.getRequestDispatcher("WEB-INF/feedback/emailCompose.jsp").forward(request, response);
		
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "reply";
	}
}
