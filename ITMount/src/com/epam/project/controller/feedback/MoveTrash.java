package com.epam.project.controller.feedback;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Feedback;
import com.epam.project.db.service.FeedbackService;

public class MoveTrash implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer id = Integer.parseInt(request.getParameter("id_trash"));
		
		Feedback feedback = FeedbackService.getFeedback(id);
		feedback.setActive(false);
		FeedbackService.updateFeedback(feedback);
		response.sendRedirect("/ITMount/FeedbackServlet?action=inbox");
		
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "moveTrash";
	}
}
