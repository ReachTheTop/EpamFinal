package com.epam.project.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.dao.CourseCorteg;
import com.epam.project.db.service.CourseService;
import com.google.gson.Gson;

public class CoursesAdministration implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson json = new Gson();
		String token = request.getParameter("search");
		String pageParam = request.getParameter("page");
		Integer page = null;
		if (token == null) {
			token = "";
		}
		if (pageParam == null) {
			page = 0;
		} else {
			page = Integer.parseInt(pageParam);
		}
		CourseCorteg result = CourseService.getAllCourses(token, page);
		response.setContentType("application/json");
		CoursesRes res = new CoursesRes();
		res.setCourses(result.getCourses());
		res.setAmount(result.getAmount());

		response.getWriter().write(json.toJson(res));

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "courses";
	}

}
