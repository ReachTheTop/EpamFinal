package com.epam.project.command.article;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Article;
import com.epam.project.db.model.Course;
import com.epam.project.db.service.ArticleService;
import com.epam.project.db.service.CourseService;

public class EditArticle implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			Integer article_id = Integer.parseInt(request.getParameter("article_id"));
			Article article = ArticleService.getById(article_id);
			
			List<Course> courses = CourseService.getAllActiveCourses();
			
			request.setAttribute("courses", courses);
			request.setAttribute("article", article);
			request.getRequestDispatcher("/WEB-INF/article/edit.jsp").forward(request, response);
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "edit";
	}

}
