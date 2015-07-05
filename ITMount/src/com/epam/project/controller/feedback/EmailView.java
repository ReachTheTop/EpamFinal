package com.epam.project.controller.feedback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Feedback;
import com.epam.project.db.service.FeedbackService;

public class EmailView implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id =Integer.parseInt(request.getParameter("id"));
		Feedback feedback = FeedbackService.getFeedback(id);
		if(!feedback.getRead()){
			feedback.setRead(true);
			FeedbackService.updateFeedback(feedback);
			request.setAttribute("newMessage", FeedbackService.getAllNotRead().size());
		}
		request.setAttribute("feedback", feedback);
		request.getRequestDispatcher("WEB-INF/feedback/emailView.jsp").forward(request, response);
		
	}
	@Override
	public String getName() {
		return "view";
	}
}


