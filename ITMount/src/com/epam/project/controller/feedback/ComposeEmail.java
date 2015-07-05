package com.epam.project.controller.feedback;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.epam.project.command.Action;
import com.epam.project.db.service.FeedbackService;

public class ComposeEmail implements Action {

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("newMessage", FeedbackService.getAllNotRead().size());
		request.getRequestDispatcher("WEB-INF/feedback/emailCompose.jsp").forward(request, response);
		
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "compose";
	}
}
