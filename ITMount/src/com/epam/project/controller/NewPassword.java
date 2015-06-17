package com.epam.project.controller;

import java.io.IOException;
import java.util.Date;

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
 * Servlet implementation class Newpassword
 */
@WebServlet("/NewPassword")
public class NewPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		String email = request.getParameter("user");
		String key = request.getParameter("key");
		String code = request.getParameter("code");
		User user = UserService.getUserWhereEmail(email);
		if(user!=null){
			if(user.getKey().equals(key)){
				user.setIs_confirmed(true);
				UserService.updateUser(user);
				sesion.setAttribute("passwordReset", "1");
				sesion.setAttribute("newPasswordEmail", email);
				sesion.setAttribute("newPasswordKey", key);
				sesion.setAttribute("newPasswordCode", code);
				request.getRequestDispatcher("WEB-INF/page/passwordReset.jsp").forward(request, response);
			}else{
				response.sendError(404);
				return;
			}
		}else{
			response.sendError(404);
			return;
		}
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		String password = request.getParameter("passwordReset");
		String password1 = request.getParameter("passwordReset1");
		String email =(String) sesion.getAttribute("newPasswordEmail");
		String key =(String) sesion.getAttribute("newPasswordKey");
		String code =(String) sesion.getAttribute("newPasswordCode");
		Date date = new Date(Long.parseLong(code));
		User user = UserService.getUserWhereEmail(email);
		if(password.equals(password1)&&date.after(new Date())&&user!=null&user.getKey().equals(key)&&user.getIs_confirmed()){
			user.setPassword_hash(SaltedMD5.getPassword(password, email));
			sesion.setAttribute("user", user);
			UserService.updateUser(user);
			response.sendRedirect("/ITMount/home");
			return;
		}else{
			response.sendError(404);
			return;
		}
	}

}
