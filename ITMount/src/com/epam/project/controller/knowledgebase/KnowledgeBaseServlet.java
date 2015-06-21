package com.epam.project.controller.knowledgebase;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Menu;

/**
 * Servlet implementation class KnowledgeBaseServlet
 */
@WebServlet("/KnowledgeBaseServlet")
@MultipartConfig
public class KnowledgeBaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Menu menu;

	public KnowledgeBaseServlet() {
		super();
		menu = new Menu(new KnowledgeIndex(), new KnowledgeNew(), new KnowledgeDelete(), new KnonwledgeActive());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		menu.execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		menu.execute(request, response);
	}

}
