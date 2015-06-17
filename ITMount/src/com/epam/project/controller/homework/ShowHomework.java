package com.epam.project.controller.homework;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.HomeWork;
import com.epam.project.db.model.Task;
import com.epam.project.db.service.HomeWorkService;
import com.epam.project.db.service.TaskService;


public class ShowHomework implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String group_id= request.getParameter("group_id");
		String user_id= request.getParameter("users_id");
		Map<HomeWork, Task> homew = new LinkedHashMap<HomeWork, Task>();
		for (HomeWork home : HomeWorkService.getAllHomeworkWhereUserID(Integer.parseInt(user_id))) {
			Task task =TaskService.getTask(home.getTask_id());
			if(task.getGroupID().equals(Integer.parseInt(group_id))){
			homew.put(home, task); 
			}
		}
		request.setAttribute("homeworks", homew);
		request.getRequestDispatcher("/WEB-INF/homework/show.jsp")
		.forward(request, response);

	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "show";
	}
}
