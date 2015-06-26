package com.epam.project.controler.uploadLanguage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Language;
import com.epam.project.db.service.LanguageService;
import com.google.gson.Gson;

public class ShowLanguage implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson json = new Gson();
		List<Language> language = LanguageService.getAllLanguagesByAmin();
		response.setContentType("application/json");
		response.getWriter().write(json.toJson(language));
		
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "show";
	}
}
