package com.epam.project.command;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Menu {

	private HashMap<String, Action> menu;
	private String action;
	private Action currentTask;
	public Menu(Action... items) {
		menu = new HashMap<String, Action>();
		
		for (Action action : items) {
			menu.put(action.getName(), action);
		}
	}
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		action = request.getParameter("action");
	
		if(action == null){
			action = "index";
		}
		currentTask =  menu.get(action);
		try{
		currentTask.execute(request, response);
		}catch(NullPointerException e){
			response.sendError(404);
			return;
		}
		
		
	}
	
}
