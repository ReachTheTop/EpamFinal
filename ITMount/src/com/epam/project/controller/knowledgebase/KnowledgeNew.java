package com.epam.project.controller.knowledgebase;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.epam.project.command.Action;
import com.epam.project.db.model.HomeWork;
import com.epam.project.db.model.KnowledgeBase;
import com.epam.project.db.service.HomeWorkService;
import com.epam.project.db.service.KnowledgeBaseService;
import com.epam.project.util.file.UploadFile;


public class KnowledgeNew implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer course_id = Integer.parseInt(request.getParameter("cours_id"));
		Part file = request.getPart("file");
		UploadFile m = new UploadFile();
		
		if (file.getSize()>0) {
			KnowledgeBase kb = new KnowledgeBase();
			kb.setCourse_id(course_id);
			
			
			String fileName = m.uploadFile(file, request.getServletContext(),"knowledgeBase_course_id_"+course_id);
			kb.setPath(fileName);
			KnowledgeBaseService.addKnowledgeBase(kb);
			
		}
		response.sendRedirect(request.getHeader("Referer"));
		return;
		
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "new";
	}

}
