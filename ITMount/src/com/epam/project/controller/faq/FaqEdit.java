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

public class FaqEdit implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer faq_id = null;
		if(request.getParameter("faq_id") == null){
			response.sendError(404);
			return;
		}else{
			faq_id = Integer.parseInt(request.getParameter("faq_id"));
		}
		Article question = ArticleService.getFAQById(faq_id);
		List<FaqCategory> categories = FaqCategoryService.getAllCategories();
		request.setAttribute("categories", categories);
		request.setAttribute("qa", question);
		request.setAttribute("action", "update&faq_id="+faq_id);
		request.getRequestDispatcher("/WEB-INF/faq/edit.jsp").forward(request, response);;
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "edit";
	}

}
