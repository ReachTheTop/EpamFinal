package com.epam.project.controller.group.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.service.GroupExamService;

public class ChouseExamDate implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer group_id = Integer.parseInt(request.getParameter("group_id"));
		Integer user_id = Integer.parseInt(request.getParameter("user_id"));
		Integer exam_date = Integer.parseInt(request.getParameter("exam_date"));
		
		GroupExamService.setExamDate(group_id, user_id, exam_date);
		
		request.getRequestDispatcher(
				"/GroupServlet?action=show&group_id=" + group_id)
				.forward(request, response);

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "chousExam";
	}

}
