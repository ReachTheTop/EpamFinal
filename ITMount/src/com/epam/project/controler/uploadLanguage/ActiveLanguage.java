package com.epam.project.controler.uploadLanguage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Language;
import com.epam.project.db.service.LanguageService;
import com.google.gson.Gson;

public class ActiveLanguage implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer language_id=Integer.parseInt(request.getParameter("language_id"));
		Language language =  LanguageService.getLanguage(language_id);
		 if(language.isActive()){
			 language.setActive(false);
		 }else{
			 language.setActive(true);
		 }
		 LanguageService.updateLanguage(language);
		 Gson json = new Gson();
			response.setContentType("application/json");
			response.getWriter().write(json.toJson(language));
		
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "active";
	}
}
