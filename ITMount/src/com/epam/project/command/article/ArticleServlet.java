package com.epam.project.command.article;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Menu;
import com.epam.project.command.admin.UserArticles;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Menu menu;

	public ArticleServlet() {
		super();
		menu = new Menu(new IndexArticle(), new CreateArticle(),
				new ShowArticle(), new NewArticle(), new UpdateArticle(),
				new EditArticle(), new TogleArticle(), new CommentArticle(),
				new UserArticles());
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		menu.execute(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		menu.execute(request, response);

	}

}
