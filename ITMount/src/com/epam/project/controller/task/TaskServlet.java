package com.epam.project.controller.task;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Menu;

@WebServlet("/TaskServlet")
@MultipartConfig
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Menu menu;

	public TaskServlet() {
        super();
       menu = new Menu(new CreateTaskCommand());
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		menu.execute(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		menu.execute(request, response);
	}

}
