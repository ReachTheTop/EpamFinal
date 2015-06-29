package com.epam.project.controller.homework;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.epam.project.command.Action;
import com.epam.project.db.model.HomeWork;
import com.epam.project.db.service.HomeWorkService;
import com.epam.project.util.file.DeleteFile;

public class DeleteHomework implements Action {

	private String message;
	private String status;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		String homework_id = request.getParameter("id_homework");
		ResourceBundle res = (ResourceBundle) request.getSession().getAttribute("bundle");
		HomeWork home = HomeWorkService.getHomeWork(Integer.parseInt(homework_id));
		DeleteFile.deleteFile(home.getData(), request.getServletContext());
		HomeWorkService.delHomeWork(Integer.parseInt(homework_id));
		status = "success";
		message = res.getString("Homework.Delete.success");
		try {
			
			response.getWriter().print(new JSONObject().put(status, message));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String getName() {
		
		return "delete";
	}
}
