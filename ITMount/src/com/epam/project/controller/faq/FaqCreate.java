package com.epam.project.controller.faq;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Article;
import com.epam.project.db.service.ArticleService;
import com.google.gson.Gson;

public class FaqCreate implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Article qa = new Article();
		qa.setHeader(request.getParameter("header"));
		qa.setCourse_id(Integer.parseInt(request.getParameter("category_id")));
		qa.setData(request.getParameter("article"));
		ArticleService.createFAQ(qa);
		response.setContentType("application/json");
		Gson json = new Gson();
		response.getWriter().write(json.toJson(qa));
	}

	@Override
	public String getName() {
		return "create";
	}

}
