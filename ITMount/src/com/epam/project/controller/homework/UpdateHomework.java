package com.epam.project.controller.homework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.epam.project.command.Action;
import com.epam.project.db.model.HomeWork;
import com.epam.project.db.service.HomeWorkService;
import com.epam.project.util.file.DeleteFile;
import com.epam.project.util.file.UploadFile;

public class UpdateHomework implements Action {

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileUpload = request.getParameter("uploadFile");
		String homework_id = request.getParameter("id_homework");
		Part file = request.getPart("file");
		
		UploadFile m = new UploadFile();
		
		if (file.getSize()>0) {
		
			String fileName = m.uploadFile(file, request.getServletContext(),"homework_id_"+homework_id);
			HomeWork homework = HomeWorkService.getHomeWork(Integer.parseInt(homework_id));
			homework.setData(fileName);
			HomeWorkService.updateHomeWork(homework);
			DeleteFile.deleteFile(fileUpload, request.getServletContext());
		}
		response.sendRedirect(request.getHeader("Referer"));
		return;
	}
	
	@Override
	public String getName(){
		return "update";
	}
}
