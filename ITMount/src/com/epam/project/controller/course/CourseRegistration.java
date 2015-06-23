package com.epam.project.controller.course;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.User;
import com.epam.project.db.service.GroupService;
import com.google.gson.Gson;

public class CourseRegistration implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = null;

		user = (User) request.getSession().getAttribute("user");

		Integer course_id = Integer.parseInt(request.getParameter("course_id"));

		Integer group_id = null;


		if (user != null && user.getCurriculum_vitae()!=null) {
			group_id = GroupService.addUserToGroup(user, course_id);
			Gson g = new Gson();
			Map map = new HashMap();
			map.put("key", group_id);
			response.setContentType("application/json");
			response.getWriter().write(g.toJson(map));;
			return;
			
		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;

		}
	}

	@Override
	public String getName() {

		return "register";
	}

}