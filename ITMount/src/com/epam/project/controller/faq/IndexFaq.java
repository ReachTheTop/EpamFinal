package com.epam.project.controller.faq;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Article;
import com.epam.project.db.model.FaqCategory;
import com.epam.project.db.service.ArticleService;
import com.epam.project.db.service.FaqCategoryService;

public class IndexFaq implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<FaqCategory> categories = FaqCategoryService.getAllCategoriesWithQA();
		
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/WEB-INF/faq/index.jsp").forward(request, response);
		
	}

	@Override
	public String getName() {
		return "index";
	}

}
