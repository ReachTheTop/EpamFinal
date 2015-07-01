package com.epam.project.controller.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Group;
import com.epam.project.db.service.GroupService;

public class GroupChat implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		Group group = GroupService.getById(Integer.parseInt(request.getParameter("group_id")));
		
		request.setAttribute("group", group);
		request.getRequestDispatcher("/WEB-INF/chat/chat.jsp").forward(request, response);
		
	}

	@Override
	public String getName() {
		return "chat";
	}
	
}
