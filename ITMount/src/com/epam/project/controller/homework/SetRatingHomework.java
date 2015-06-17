package com.epam.project.controller.homework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.HomeWork;
import com.epam.project.db.service.HomeWorkService;

public class SetRatingHomework implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String homework_id = request.getParameter("id_homework");
		String ratingS = request.getParameter("rating");
		Integer rating;
		HomeWork homework = HomeWorkService.getHomeWork(Integer.parseInt(homework_id));
		try {

			rating = Integer.parseInt(ratingS);

			if (rating > 100 || rating < 0) {
				throw new Exception();
			}
		} catch (Exception e) {
			response.sendRedirect(request.getHeader("Referer"));
			return;
		}
		homework.setRating(rating);
		HomeWorkService.updateHomeWork(homework);
		response.sendRedirect(request.getHeader("Referer"));
		return;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "rating";
	}
}
