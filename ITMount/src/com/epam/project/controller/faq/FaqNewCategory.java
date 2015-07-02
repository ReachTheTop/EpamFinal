package com.epam.project.controller.faq;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.FaqCategory;
import com.epam.project.db.service.FaqCategoryService;
import com.google.gson.Gson;

public class FaqNewCategory implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String category_name = request.getParameter("category");
		FaqCategory category = new FaqCategory();
		category.setCategory(category_name);
		Integer category_id = FaqCategoryService.addCategory(category);
		category.setId(category_id);
		Gson json = new Gson();
		response.setContentType("application/json");
		response.getWriter().write(json.toJson(category));
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "newCategory";
	}

}
