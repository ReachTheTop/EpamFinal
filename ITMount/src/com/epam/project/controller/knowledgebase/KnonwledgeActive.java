package com.epam.project.controller.knowledgebase;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.epam.project.command.Action;
import com.epam.project.db.model.KnowledgeBase;
import com.epam.project.db.service.KnowledgeBaseService;

public class KnonwledgeActive implements Action {
	private String message;
	private String status;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		String idKbase = request.getParameter("id_kbase");
		JSONObject json = new JSONObject();
		ResourceBundle res = (ResourceBundle) request.getSession().getAttribute("bundle");
		KnowledgeBase knowledge = KnowledgeBaseService.getKnowledgeBase(Integer.parseInt(idKbase));
	 	knowledge.setAvailable(!knowledge.getAvailable());
	 	KnowledgeBaseService.updateKnowledgeBase(knowledge);
	 	status = "success";
	 	if(knowledge.getAvailable()){
	 		message = res.getString("knowledgebase.isActive");
	 	}else{
	 		message = res.getString("knowledgebase.isNotActive");
	 	}
	 	
		
		try {
			json.put("available", knowledge.getAvailable());
			json.put(status, message);
			response.getWriter().print(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "active";
	}
}
