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

/**
 * Servlet implementation class ConfirmEmail
 */

public class ConfirmEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String key =request.getParameter("key");
		String email =request.getParameter("email");
		User user = UserService.getUserWhereEmail(email);
		if(user!=null){
			if(user.getKey().equals(key)){
				user.setIs_confirmed(true);
				UserService.updateUser(user);
				session.setAttribute("infoconfirm", "1");
			}
		}
		request.getRequestDispatcher("WEB-INF/page/login.jsp").forward(request,
				response);
	}


}
