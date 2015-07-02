package com.epam.project.controller.faq;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Article;
import com.epam.project.db.service.ArticleService;
import com.google.gson.Gson;

public class FaqUpdate implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer faq_id = null;
		Article question = null;
		if(request.getParameter("faq_id")== null){
			response.sendError(404);
			return;
		}
		faq_id = Integer.parseInt(request.getParameter("faq_id"));
		question = new Article();
		question.setId(faq_id);
		question.setHeader(request.getParameter("header"));
		question.setData(request.getParameter("article"));
		question.setCourse_id(Integer.parseInt(request.getParameter("category_id")));
		ArticleService.updateArticle(question);
		response.setContentType("application/json");
		Gson json = new Gson();
		response.getWriter().write(json.toJson(question));
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "update";
	}

}
