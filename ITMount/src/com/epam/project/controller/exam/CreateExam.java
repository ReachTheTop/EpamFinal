package com.epam.project.controller.exam;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.GroupExamModel;
import com.epam.project.db.service.GroupExamService;

public class CreateExam implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GroupExamModel exam = new GroupExamModel();
		
		exam.setDescription(request.getParameter("description"));
		exam.setGroup_id(Integer.parseInt(request.getParameter("group_id") ));
		
		String examDateRaw= request.getParameter("exam_date");

		
		Date examDate = null;
		DateFormat deadline = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		try {
			examDate= deadline.parse(examDateRaw);
		} catch (ParseException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}

		exam.setExam_date(examDate);
		
		GroupExamService.createExam(exam);
		
	}

	@Override
	public String getName() {

		return "create";
	}

}
