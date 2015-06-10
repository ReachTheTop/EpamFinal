package com.epam.project.controller.knowledgebase;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.service.KnowledgeBaseService;

public class KnowledgeIndex implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("base",
				KnowledgeBaseService.getAllKnowledgeBases());
		request.getRequestDispatcher("/WEB-INF/knowledge_base/index.jsp")
				.forward(request, response);

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "index";
	}

}
