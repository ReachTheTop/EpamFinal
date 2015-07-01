package com.epam.project.controller.group;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Message;
import com.epam.project.db.service.MessageService;
import com.google.gson.Gson;

public class GroupChatHistory implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String param = request.getParameter("group_id");
		Integer group_id = Integer.parseInt(param);
		Integer last_message = null;
		if (request.getParameter("last_id") != null) {
			last_message = Integer.parseInt(request.getParameter("last_id"));
		}
		List<Message> messages = MessageService.getChatHistory(group_id, last_message);

		Gson json = new Gson();
		response.setContentType("application/json");
		response.getWriter().write(json.toJson(messages));
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "chatHistory";
	}

}
