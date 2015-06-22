package com.epam.project.controller.exam;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.GroupExamModel;
import com.epam.project.db.service.GroupExamService;

public class UpdateExam implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer exam_id = Integer.parseInt(request.getParameter("exam_id"));
		GroupExamModel exam = GroupExamService.getById(exam_id);
		exam.setDescription(request.getParameter("description"));
		try {
			exam.setExam_date(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
					.parse(request.getParameter("date")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			response.sendRedirect(request.getHeader("Referer"));
		}
		
		
		
		GroupExamService.updateExam(exam);
		
		response.sendRedirect(request.getHeader("Referer"));
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "update";
	}

}
