package com.epam.project.controller.exam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Menu;

/**
 * Servlet implementation class GroupExamServlet
 */
@WebServlet("/GroupExamServlet")
public class GroupExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private Menu menu;
    public GroupExamServlet() {
        super();
        menu = new Menu(new CreateExam());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		menu.execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		menu.execute(request, response);
	}

}
