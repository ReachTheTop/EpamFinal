package com.epam.project.command.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Article;
import com.epam.project.db.model.User;
import com.epam.project.db.service.ArticleService;

public class UserArticles implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		List<Article> articles = ArticleService.getByAuthor(user.getId());
		
		request.setAttribute("articles", articles);
		request.getRequestDispatcher("/WEB-INF/article/user_articles.jsp").forward(request, response);
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "myarticles";
	}

}
