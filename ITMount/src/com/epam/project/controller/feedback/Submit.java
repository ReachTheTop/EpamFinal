package com.epam.project.controller.feedback;

import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.epam.project.command.Action;
import com.epam.project.db.model.Feedback;
import com.epam.project.db.service.FeedbackService;
import com.epam.project.mailer.Mailer;

public class Submit implements Action {


	private String message;
	private String status;
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		String text = request.getParameter("message").trim();
		String subject = request.getParameter("subject");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		Feedback feedback = new Feedback();
		ResourceBundle res = (ResourceBundle) request.getSession().getAttribute("bundle");

			feedback.setEmail(email);
			feedback.setSend(true);
			feedback.setTimeMessage(new Date());
			if(type.isEmpty()){
				feedback.setType("Message");
			}else{
				feedback.setType(type);
			}
			
			feedback.setContent(text);
			if(name.isEmpty()){
				feedback.setName(subject);
			}else{
				feedback.setName(name);
			}
			if(feedback.isValid()){
			FeedbackService.addFeedback(feedback);
			sendMail(email, subject, text);
			status = "success";
			message =res.getString("Feedback.message.sendEmail");
		}else{
			System.out.println(feedback.validate());
			if(feedback.validate().containsKey("email")){
				
				status = "fail";
				message =res.getString("Feedback.message.incorrectEmail");
			}else{
				
				status = "fail";
				message =res.getString("Feedback.message.emptyField");
			}
			
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
		return "submit";
	}
	
	private void sendMail(final String email, final  String subject, final String message) {
		
		
		
		Thread mailer = new Thread(new Runnable() {
			@Override
			public void run() {
				
					try {
						Mailer.sendEmail(email,subject, message);
					} catch (AddressException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			
		});
		mailer.start();
	}
}
