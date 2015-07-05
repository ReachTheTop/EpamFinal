package com.epam.project.controller.feedback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Feedback;
import com.epam.project.db.service.FeedbackService;

public class ShowMessage implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filter = request.getParameter("filter");
		List<Feedback> list = new ArrayList<Feedback>();
		if(filter!=null){
			list = FeedbackService.getAllMessageFilter(filter, true, false, false);
		}else{
			list = FeedbackService.getAllFeedbacks();
		}
		

	
		Collections.reverse(list);
		
		request.setAttribute("feedbacks", list);
		request.setAttribute("title", "Inbox ");
		request.setAttribute("type", "inbox");
		request.setAttribute("newMessage", FeedbackService.getAllNotRead().size());
		request.getRequestDispatcher("WEB-INF/feedback/index.jsp").forward(request, response);
	}
	@Override
	public String getName() {
		return "inbox";
	}
}
