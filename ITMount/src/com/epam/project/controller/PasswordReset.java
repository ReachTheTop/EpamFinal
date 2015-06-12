package com.epam.project.controller;

import java.io.IOException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.project.db.model.User;
import com.epam.project.db.service.UserService;
import com.epam.project.mailer.Mailer;
import com.epam.project.md5.SaltedMD5;

/**
 * Servlet implementation class ResetPassword
 */

public class PasswordReset extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordReset() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		sesion.removeAttribute("inforeset");
		sesion.removeAttribute("modalreset");
		request.getRequestDispatcher("WEB-INF/page/passwordReset.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		String email = request.getParameter("resetemail");
		
		sesion.removeAttribute("inforeset");
		sesion.removeAttribute("modalreset");
		User user = UserService.getUserWhereEmail(email);
		if(user!=null){
			Integer key = new Random().nextInt(Integer.MAX_VALUE);
			String password = key.toString();
			user.setPassword_hash(SaltedMD5.getPassword(password, user.getEmail()));
			
			try {
				Mailer.sendEmail(user.getEmail(), "Reset password", "New password: " +password);
				UserService.addNewUser(user);
				sesion.setAttribute("inforeset", "На вашу скриньку відправлено новий пароль");
				sesion.setAttribute("modalreset", "1");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			sesion.setAttribute("inforeset", "Error! "+email+" не існує");
			sesion.setAttribute("modalreset", "1");
		}
		
	
		request.getRequestDispatcher("WEB-INF/page/passwordReset.jsp").forward(request, response);
	}

}
