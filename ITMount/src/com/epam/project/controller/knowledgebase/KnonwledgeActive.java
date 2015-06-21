package com.epam.project.controller.knowledgebase;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.KnowledgeBase;
import com.epam.project.db.service.KnowledgeBaseService;

public class KnonwledgeActive implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idKbase = request.getParameter("id_kbase");
		String available = request.getParameter("active");
		KnowledgeBase knowledge = KnowledgeBaseService.getKnowledgeBase(Integer.parseInt(idKbase));
	 	if(available.equals("true")){
	 		knowledge.setAvailable(true);
	 		
	 	}else{
	 		knowledge.setAvailable(false);
	 	}

	 	KnowledgeBaseService.updateKnowledgeBase(knowledge);
		
		response.sendRedirect(request.getHeader("Referer"));
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "active";
	}
}
