package com.epam.project.controller.homework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.service.HomeWorkService;
import com.epam.project.util.file.DeleteFile;

public class DeleteHomework implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String file = request.getParameter("deleteFile");
		String homework_id = request.getParameter("id_homework");
		DeleteFile.deleteFile(file, request.getServletContext());
		HomeWorkService.delHomeWork(Integer.parseInt(homework_id));
		response.sendRedirect(request.getHeader("Referer"));
	}
	@Override
	public String getName() {
		
		return "delete";
	}
}
