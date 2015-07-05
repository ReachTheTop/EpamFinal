package com.epam.project.controller.feedback;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Feedback;
import com.epam.project.db.service.FeedbackService;

public class Search implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String search = request.getParameter("search");
		String type = request.getParameter("type");
		
		
			if(type.equals("inbox")){
			List<Feedback> list = FeedbackService.getAllSearch(true, false, false, search);
			Collections.reverse(list);
			request.setAttribute("feedbacks", list);
			request.setAttribute("title", "Inbox ");
			request.setAttribute("type", "inbox");
			request.setAttribute("newMessage", FeedbackService.getAllNotRead().size());
			request.getRequestDispatcher("WEB-INF/feedback/index.jsp").forward(request, response);
			}
			else if(type.equals("trash")){
				List<Feedback> list = FeedbackService.getAllSearch(false, search);
				Collections.reverse(list);
				
				request.setAttribute("feedbacks", list);
				request.setAttribute("title", "Trash ");
				request.setAttribute("type", "trash");
				request.setAttribute("newMessage", FeedbackService.getAllNotRead().size());
				request.getRequestDispatcher("WEB-INF/feedback/index.jsp").forward(request, response);
			}
			else if(type.equals("important")){
				List<Feedback> list = FeedbackService.getAllSearch(true, false, true, search);
				Collections.reverse(list);
				request.setAttribute("feedbacks", list);
				request.setAttribute("title", "Important ");
				request.setAttribute("type", "important");
				request.setAttribute("newMessage", FeedbackService.getAllNotRead().size());
				request.getRequestDispatcher("WEB-INF/feedback/index.jsp").forward(request, response);
			}
			else if(type.equals("send")){
				List<Feedback> list = FeedbackService.getAllSearch(true, true, false, search);
				Collections.reverse(list);
				request.setAttribute("feedbacks", list);
				request.setAttribute("title", "Send Mail ");
				request.setAttribute("type", "send");
				request.setAttribute("newMessage", FeedbackService.getAllNotRead().size());
				request.getRequestDispatcher("WEB-INF/feedback/index.jsp").forward(request, response);
			}
			else{
				request.getRequestDispatcher("WEB-INF/feedback/index.jsp").forward(request, response);
			}
		
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "search";
	}
}
