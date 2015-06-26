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

public class TogleArticle implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer article_id = Integer.parseInt(request.getParameter("article_id"));
		ArticleService.togleArticle(article_id);
		Article article = ArticleService.getById(article_id);
		JSONObject json = new JSONObject();
		try {
			json.put("active", article.getIs_active());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.getWriter().print(json);
		
		//request.getRequestDispatcher("ArticleServlet?action=index").forward(request, response);
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "togle";
	}

}
