package com.epam.project.controller.group;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Message;
import com.epam.project.db.model.User;
import com.epam.project.db.service.GroupUserService;
import com.epam.project.db.service.MessageService;
import com.epam.project.db.service.UserService;

public class LeavUsersInGroup implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<String> users = new ArrayList<String>(Arrays.asList(request
				.getParameterValues("users")));
		users.removeAll(Arrays.asList("", null));

		Integer group_id = Integer.parseInt(request.getParameter("group_id"));

		Message passed = new Message();
		Message failed = new Message();
		passed.setSubject(request.getParameter("subject"));
		User sender = (User) request.getSession().getAttribute("user");
		passed.setSender_id(sender.getId());
		passed.setContent(request.getParameter("messagePassed"));

		failed.setSubject(request.getParameter("subject"));
		failed.setSender_id(sender.getId());
		failed.setContent(request.getParameter("messageFailed"));
		
		List<Integer> users_id = new ArrayList<Integer>();

		for (String email : users) {
			users_id.add(UserService.getUserWhereEmail(email).getId());
		}
		MessageService.sendMessageToUsers(passed, users_id);
		MessageService.sendMessageToRest(failed, group_id, users_id);
		
		GroupUserService.leaveUsersInGroup(group_id, users);
		
		request.getRequestDispatcher(
				"/GroupServlet?action=show&group_id=" + group_id).forward(
				request, response);
		return;

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "leave";
	}

}
