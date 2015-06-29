package com.epam.project.controller.homework;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.epam.project.command.Action;
import com.epam.project.db.model.HomeWork;
import com.epam.project.db.service.HomeWorkService;

public class SetRatingHomework implements Action {

	private String message;
	private String status;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		ResourceBundle res = (ResourceBundle) request.getSession().getAttribute("bundle");
		Integer homework_id =Integer.parseInt( request.getParameter("id_homework"));
		Integer rating =Integer.parseInt(request.getParameter("rating"));
		
		HomeWork homework = HomeWorkService.getHomeWork(homework_id);
		try {

			

			if (rating > 10 || rating <0) {
				throw new Exception();
			}
			status = "success";
			message = res.getString("Homework.setRating");
			homework.setRating(rating);
			HomeWorkService.updateHomeWork(homework);
		} catch (Exception e) {
			status = "fail";
			message = res.getString("Homework.setRating.error");
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
		return "rating";
	}
}
