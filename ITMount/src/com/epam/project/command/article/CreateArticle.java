package com.epam.project.command.article;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.epam.project.command.Action;
import com.epam.project.db.model.User;

public class CreateArticle implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Article article = new Article();
		User user = (User) request.getSession().getAttribute("user");
		String header = request.getParameter("header");
		String data = request.getParameter("article");
		Integer course_id = Integer.parseInt(request.getParameter("course_id"));
		Integer user_id = user.getId();
		
		article.setHeader(header);
		article.setData(data);
		article.setCourse_id(course_id);
		article.setUser_id(user_id);
		Integer article_id = ArticleService.createArticle(article);
		
		//request.getRequestDispatcher("/ArticleServlet?action=show&article_id="+ article_id).forward(request, response);
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
		return "create";
	}

}
