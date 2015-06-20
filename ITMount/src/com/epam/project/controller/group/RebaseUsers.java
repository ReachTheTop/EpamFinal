package com.epam.project.controller.group;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Group;
import com.epam.project.db.service.GroupService;
import com.epam.project.db.service.GroupUserService;

public class RebaseUsers implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<String> users = new ArrayList<String>(Arrays.asList(request
				.getParameterValues("users")));
		users.removeAll(Arrays.asList("", null));

		String marker = request.getParameter("marker");
		Group current_group = GroupService.getById(Integer.parseInt(request
				.getParameter("group_id")));
		Group newGroup = null;
		if (marker != null) {
			newGroup = GroupService.getById(Integer.parseInt(request
					.getParameter("new_group_id")));
			GroupUserService.rebaseUsers(current_group.getId(),
					newGroup.getId(), users);
		} else {
			newGroup = new Group();
			newGroup.setCourse_id(current_group.getCourse_id());
			newGroup.setTeacher_id(current_group.getTeacher_id());
			newGroup.setName(request.getParameter("name"));
			Integer group_id = GroupService.newGroup(newGroup);
			GroupUserService
					.rebaseUsers(current_group.getId(), group_id, users);
		}

		response.sendRedirect(request.getHeader("Referer"));
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "rebase";
	}

}
