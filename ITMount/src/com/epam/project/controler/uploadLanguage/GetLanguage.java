package com.epam.project.controler.uploadLanguage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Language;
import com.epam.project.db.service.LanguageService;
import com.google.gson.Gson;

public class GetLanguage implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer language = Integer.parseInt(request.getParameter("language_id"));
		Language lang = LanguageService.getLanguage(language);
		Gson json = new Gson();
		response.setContentType("application/json");
		response.getWriter().write(json.toJson(lang));
		
	}
	@Override
	public String getName() {
		return "get";
	}
}
