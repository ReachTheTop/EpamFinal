package com.epam.project.controller.exam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.GroupExamModel;
import com.epam.project.db.service.GroupExamService;
import com.google.gson.Gson;

public class ShowExam implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer exam_id = Integer.parseInt(request.getParameter("exam_id"));
		GroupExamModel exam = GroupExamService.getById(exam_id);
		Gson json = new Gson();

		response.setContentType("application/json");
		response.getWriter().write(json.toJson(exam));

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "show";
	}

}
