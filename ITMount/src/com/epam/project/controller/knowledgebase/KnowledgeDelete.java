package com.epam.project.controller.knowledgebase;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.service.HomeWorkService;
import com.epam.project.db.service.KnowledgeBaseService;
import com.epam.project.util.file.DeleteFile;

public class KnowledgeDelete implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idKbase = request.getParameter("id_kbase");
		String deleteFile = request.getParameter("deleteFile");
		DeleteFile.deleteFile(deleteFile, request.getServletContext());
		KnowledgeBaseService.delKnowledgeBase(Integer.parseInt(idKbase));
		response.sendRedirect(request.getHeader("Referer"));

	}

	@Override
	public String getName() {

		return "delete";
	}

}
