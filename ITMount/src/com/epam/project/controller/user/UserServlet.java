package com.epam.project.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.db.model.Group;
import com.epam.project.db.model.User;
import com.epam.project.db.service.GroupService;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User current_user = (User) request.getSession().getAttribute("user");
		List<Group> groups = null;
		if (current_user.getRole().equals("applicant")
				|| current_user.getRole().equals("student")) {
			groups = GroupService.getGroupsUserStudy(current_user.getId());

			request.setAttribute("groups", groups);
		} else if (current_user.getRole().equals("lecturer")) {
			groups = GroupService.getByTeacher(current_user.getId());
			request.setAttribute("groups", groups);
		}
		request.getRequestDispatcher("/WEB-INF/user/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
