package com.epam.project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.project.db.model.User;
import com.epam.project.db.service.UserService;
import com.epam.project.md5.SaltedMD5;

/**
 * Servlet implementation class Login
 */

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.setAttribute("errorLogin", null);
		request.getRequestDispatcher("WEB-INF/page/login.jsp").forward(request,
				response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String email = request.getParameter("emaill");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		response.setCharacterEncoding("utf-8");
		if (email.isEmpty() || password.isEmpty()) {
			session.setAttribute("emaill", email);
			session.setAttribute("errorLogin", "Wrong email or password");
			request.getRequestDispatcher("WEB-INF/page/login.jsp").forward(
					request, response);
			
		} else {
			User user = UserService.getUserWhereEmail(email);
			if (user != null
					&& user.getPassword_hash().equals(
							SaltedMD5.getPassword(password, email))) {
				if(!user.getIs_confirmed()){
					
					session.setAttribute("confirmemail", 1);
					session.setAttribute("userkey", user.getKey());
					session.setAttribute("useremail", user.getEmail());
					request.getRequestDispatcher("WEB-INF/page/login.jsp").forward(
							request, response);
					
				}else{
					session.setAttribute("user", user);	
					
					response.sendRedirect("/ITMount/UserServlet");
					return;
				}
				
				

			} else {

				session.setAttribute("emaill", email);
				session.setAttribute("errorLogin", " Wrong email or password");
				request.getRequestDispatcher("WEB-INF/page/login.jsp").forward(
						request, response);
				return;
			}

		}

		//request.getParameter("check");

	}

}
