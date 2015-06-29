package com.epam.project.controller.homework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.epam.project.command.Action;
import com.epam.project.db.model.HomeWork;
import com.epam.project.db.service.HomeWorkService;
import com.epam.project.util.file.UploadFile;

public class UploadHomework implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer task_id = Integer.parseInt(request.getParameter("task_id"));
		Integer user_id = Integer.parseInt(request.getParameter("user_id"));
		Part file = request.getPart("file");
		UploadFile m = new UploadFile();
		
		if (file.getSize()>0) {
			HomeWork homeWork = new HomeWork();
			homeWork.setRating(-1);
			homeWork.setTask_id(task_id);
			homeWork.setUser_id(user_id);
			HomeWorkService.addHomeWork(homeWork);
			homeWork = HomeWorkService.getHomeworkWhereUserTask(user_id, task_id);
			
			String fileName = m.uploadFile(file, request.getServletContext(),"homework_id_"+homeWork.getId());
			homeWork.setData(fileName);
			
			HomeWorkService.updateHomeWork(homeWork);
			
		}
		response.sendRedirect(request.getHeader("Referer"));
		return;
		
	}
	@Override
	public String getName() {
		
		return "upload";
	}
}
