package com.epam.project.controller.faq;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.FaqCategory;
import com.epam.project.db.service.FaqCategoryService;
import com.google.gson.Gson;

public class EditCategory implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer category_id = Integer.parseInt( request.getParameter("category_id"));
		String name = request.getParameter("categoryName");
		FaqCategory category = new FaqCategory();
		category.setCategory(name);
		category.setId(category_id);
		
		FaqCategoryService.updateCategory(category);
		
		Gson json = new Gson();
		response.setContentType("application/json");
		response.getWriter().write(json.toJson(category));
	}

	@Override
	public String getName() {
		
		return "editCategory";
	}

}
