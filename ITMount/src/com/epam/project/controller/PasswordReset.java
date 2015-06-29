package com.epam.project.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.project.db.model.Message;
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession sesion = request.getSession();
		sesion.removeAttribute("inforeset");
		sesion.removeAttribute("modalreset");
		request.getRequestDispatcher("WEB-INF/page/passwordReset.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession sesion = request.getSession();
		ResourceBundle res = (ResourceBundle) sesion.getAttribute("bundle");
		String email = request.getParameter("resetemail");
		
		sesion.removeAttribute("inforeset");
		sesion.removeAttribute("modalreset");
		User user = UserService.getUserWhereEmail(email);
		if(user!=null){
			
				
				
			sendMail(user);
				sesion.setAttribute("inforeset", res.getString("login.passwordReset"));
				sesion.setAttribute("modalreset", "1");
			
			
		}else{
			sesion.setAttribute("inforeset", "Error! "+email+" ");
			sesion.setAttribute("modalreset", "1");
		}
		
	
		request.getRequestDispatcher("WEB-INF/page/passwordReset.jsp").forward(request, response);
	}
	
	private void sendMail(final User user) {
		
		
		
		Thread mailer = new Thread(new Runnable() {
			@Override
			public void run() {
				
					try {
						Calendar cal = Calendar.getInstance();
						cal.setTime(new Date());
						cal.add(Calendar.MINUTE, 30);
						Mailer.sendEmail(user.getEmail(), "Reset password", "<a href=\"http://localhost:8080/ITMount/NewPassword?user="+user.getEmail()+"&key="+user.getKey()+"&code="+cal.getTime().getTime()+"\">New pass</a>");
					} catch (AddressException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			
		});
		mailer.start();
	}

}
