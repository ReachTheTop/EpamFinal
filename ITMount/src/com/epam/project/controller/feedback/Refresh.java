package com.epam.project.controller.feedback;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;

public class Refresh implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getHeader("Referer"));
		
		
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "refresh";
	}
}
