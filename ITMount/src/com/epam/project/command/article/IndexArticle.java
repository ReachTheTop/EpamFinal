package com.epam.project.command.article;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;

public class IndexArticle implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String token = request.getParameter("token");
		Integer page = null;
		
		if(request.getParameter("page") == null){
			page = 0;
		}else{
			page = Integer.parseInt(request.getParameter("page"));
		}
		if(token == null){
			token = "";
		}
		ArticleCorteg corteg = ArticleService.getAll(token, page);
		request.setAttribute("articles", corteg.getArticles());
		request.setAttribute("pages", corteg.getAmount());
		request.setAttribute("page", page+1);
		request.setAttribute("token", token);
		request.getRequestDispatcher("/WEB-INF/article/index.jsp").forward(
				request, response);

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "index";
	}

}
