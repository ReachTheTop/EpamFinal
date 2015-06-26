package com.epam.project.controler.uploadLanguage;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.epam.project.command.Action;
import com.epam.project.db.model.Language;
import com.epam.project.db.service.LanguageService;

public class DeleteLanguage implements Action{

	private String message;
	private String status;
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		Integer id = Integer.parseInt(request.getParameter("language_id"));
		Language language = LanguageService.getLanguage(id);
		String pachBundle = request.getRealPath("") + "WEB-INF\\classes\\";
		try{

			if(language==null){
				message ="Error delete!";
				throw new Exception();
			}
			if(language.getCountry().equals("US")&&language.getLanguage().equals("en")){
				message ="Sorry this base language";
				throw new Exception();
			}
			ResourceBundle res = (ResourceBundle) request.getSession().getAttribute("bundle");

			if(res!=null&&res.getLocale().getCountry().equals(language.getCountry())&&res.getLocale().getLanguage().equals(language.getLanguage())){
				message ="Error this language available";
				throw new Exception();
			}
			
			File deleteFile= new File(pachBundle+"i18n_"+language.getLanguage()+"_"+language.getCountry()+".properties");
			deleteFile.delete();
			LanguageService.delLanguage(id);
			status ="success";
			message="Language delete";
			
			
		}
		catch(Exception e){
			
			status ="fail";
		}
	
		try {
			
			response.getWriter().print(new JSONObject().put(status, message));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "delete";
	}
}
