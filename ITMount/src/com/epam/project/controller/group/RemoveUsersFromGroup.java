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

public class RemoveUsersFromGroup implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<String> users = new ArrayList<String>(Arrays.asList(request
				.getParameterValues("users")));
		users.removeAll(Arrays.asList("", null));

		Integer group_id = Integer.parseInt(request.getParameter("group_id"));

		User user = (User) request.getSession().getAttribute("user");
		
		Message message = new Message();
		message.setSubject(request.getParameter("subject"));
		message.setContent(request.getParameter("message"));
		message.setSender_id(user.getId());

		List<Integer> users_id = new ArrayList<Integer>();

		for (String email : users) {
			users_id.add(UserService.getUserWhereEmail(email).getId());
		}
		MessageService.sendMessageToUsers(message, users_id);

		GroupUserService.deleteUsersFromGroup(users, group_id);

		request.getRequestDispatcher(
				"/GroupServlet?action=show&group_id=" + group_id).forward(
				request, response);
		return;

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "remove";
	}

}
