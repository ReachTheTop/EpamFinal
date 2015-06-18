package com.epam.project.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.service.GroupService;

public class ConfirmGroup implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getParameter("group_id") == null){
			response.setContentType("text/html");
			response.sendError(404);
			return;
		}else{
			
			Integer group_id = Integer.parseInt(request.getParameter("group_id"));
			GroupService.confirmGroup(group_id);
			response.setContentType("application/json; charset=UTF-8");
			return;
		}
		 
		
	}

	@Override
	public String getName() {
		
		return "confirm";
	}

	
}
