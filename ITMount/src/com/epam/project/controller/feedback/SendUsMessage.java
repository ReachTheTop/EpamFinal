package com.epam.project.controller.feedback;

import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;

import javax.print.attribute.standard.Fidelity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.epam.project.command.Action;
import com.epam.project.db.model.Feedback;
import com.epam.project.db.service.FeedbackService;

public class SendUsMessage implements Action {

	private String message;
	private String status;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		ResourceBundle res = (ResourceBundle) request.getSession().getAttribute("bundle");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String message = request.getParameter("message");
		String type = request.getParameter("type");

		Feedback feedback = new Feedback();
		feedback.setName(name);
		feedback.setEmail(email);
		feedback.setContent(message);
		feedback.setType(type);
		feedback.setTimeMessage(new Date());
		feedback.setSend(false);
		if(feedback.isValid()){
			FeedbackService.addFeedback(feedback);
			status = "success";
			message = res.getString("Feedback.index.succcess.message");

		}else{
			status = "fail";
			message = res.getString("Feedback.index.fail.message");
		}
		
		try {
			
			response.getWriter().print(new JSONObject().put(status, message));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public String getName() {
		return "sendUser";
	}
}
