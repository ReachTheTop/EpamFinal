package com.epam.project.controller.group;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Group;
import com.epam.project.db.model.User;
import com.epam.project.db.service.GroupService;
import com.google.gson.Gson;

public class GetTeacherGroups implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User teacher = GroupService.getById(
				Integer.parseInt(request.getParameter("group_id")))
				.getTeacher();
		
		
			if (teacher == null ) {
				response.sendError(404);
				return;
			}
		
		List<Group> groups = GroupService.getByTeacher(teacher.getId());
		Gson json = new Gson();
		response.setContentType("application/json");
		response.getWriter().write(json.toJson(groups));

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "getTeacherGroups";
	}

}
