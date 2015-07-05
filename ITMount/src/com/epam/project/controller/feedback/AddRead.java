package com.epam.project.controller.feedback;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.epam.project.command.Action;
import com.epam.project.db.model.Feedback;
import com.epam.project.db.service.FeedbackService;

public class AddRead implements Action {

	
		private String message;
		private String status;
		@Override
		public void execute(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			response.setContentType("application/json");
			String[] list = request.getParameterValues("check");
			ResourceBundle res = (ResourceBundle) request.getSession().getAttribute("bundle");
			if(list!=null){
				for (String string : list) {
					Integer id = Integer.parseInt(string);
					Feedback feedback = FeedbackService.getFeedback(id);
						
						feedback.setRead(!feedback.getRead());
						FeedbackService.updateFeedback(feedback);
				
						
					
				}
				status = "success";
				message =res.getString("Feedback.message.addRead");
			}else{
				status = "fail";
				message =res.getString("Feedback.message.addImporatn.fail");
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
		// TODO Auto-generated method stub
		return "addRead";
	}
}
