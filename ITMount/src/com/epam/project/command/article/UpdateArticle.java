package com.epam.project.command.article;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.epam.project.command.Action;
import com.epam.project.db.model.Article;
import com.epam.project.db.service.ArticleService;

public class UpdateArticle implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer article_id = Integer.parseInt(request
				.getParameter("article_id"));
		Article article = ArticleService.getById(article_id);
		Integer course_id = null;
		if (request.getParameter("course_id") != null) {
			course_id = Integer.parseInt(request.getParameter("course_id"));
		}
		String header = request.getParameter("header");
		String data = request.getParameter("article");

		article.setCourse_id(course_id);
		article.setHeader(header);
		article.setData(data);

		ArticleService.updateArticle(article);

		JSONObject json = new JSONObject();
		try {
			json.put("id", article_id);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.getWriter().print(json);

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "update";
	}

}
