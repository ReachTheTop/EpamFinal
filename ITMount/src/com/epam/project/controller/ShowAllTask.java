package com.epam.project.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.controller.task.ValideDate;
import com.epam.project.db.model.HomeWork;
import com.epam.project.db.model.Task;
import com.epam.project.db.service.HomeWorkService;
import com.epam.project.db.service.TaskService;

public class ShowAllTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowAllTask() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		int group_id = Integer.parseInt(request.getParameter("group_id"));
		// String user_id= request.getParameter("users_id");

		String user_id = "4";
		Map<Task, HomeWork> tasks = new LinkedHashMap<Task, HomeWork>();

		List<Task> listTask = ValideDate.getTasksAfterValideDate(group_id);

		for (Task task : listTask) {
			HomeWork homeWork;
			homeWork = HomeWorkService.getHomeworkWhereUserTask(
					Integer.parseInt(user_id), task.getId());

			tasks.put(task, homeWork);
		}

		request.setAttribute("listtasks", tasks);
		request.setAttribute("group_id", group_id);

		// for (HomeWork home :
		// HomeWorkService.getAllHomeworkWhereUserID(Integer.parseInt(user_id)))
		// {
		// Task task =TaskService.getTask(home.getTask_id());
		// if(task.getGroupID().equals(Integer.parseInt(group_id))){
		// homew.put(home, task);
		// }
		// }
		// request.setAttribute("homeworks", homew);

		request.getRequestDispatcher("WEB-INF/task/AllTaskUser.jsp").forward(
				request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("post");

	}

}
