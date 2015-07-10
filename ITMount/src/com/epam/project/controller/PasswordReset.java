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
			
				
				
			sendMail(user, res);
				sesion.setAttribute("inforeset", res.getString("login.passwordReset"));
				sesion.setAttribute("modalreset", "1");
			
				
		}else{
			sesion.setAttribute("inforeset",res.getString("login.UsernameOrEmail")+" \""+ email+"\" "+res.getString("login.passwordReset.noEmail"));
			sesion.setAttribute("modalreset", "1");
		}
		
	
		request.getRequestDispatcher("WEB-INF/page/passwordReset.jsp").forward(request, response);
	}
	
	private void sendMail(final User user,final ResourceBundle res) {
		
		
		
		Thread mailer = new Thread(new Runnable() {
			@Override
			public void run() {
				
					try {
						Calendar cal = Calendar.getInstance();
						cal.setTime(new Date());
						cal.add(Calendar.MINUTE, 30);
						Mailer.sendEmail(user.getEmail(), res.getString("passwordReset.ResetPassword")," <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" bgcolor=\"#f2f2f2\">"
								+"<tbody>"
								+"<tr>"
									+"<td valign=\"top\" style=\"padding: 30px 10px;\">"
										+"<table width=\"580\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">"
							+"<tbody>"
							
									+"</tr>"
									+"<tr>"
									+"<td style=\"padding-bottom: 20px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: rgb(231, 232, 231); font-family: arial; font-size: 16px; font-weight: bold; color: rgb(70, 70, 70);\">"+res.getString("passwordReset.ConfirmNewPassword.resete.subject")+"</td>"
									+"</tr>"
									+"<tr>"
									+"<td style=\"padding-top: 20px; font-size: 14px; line-height: 1.75; color: rgb(116, 118, 122);\">"+res.getString("passwordReset.ConfirmNewPassword.resete.message")+"</td>"
									+"</tr>"
									+"<tr>"
									+"<td style=\"padding-top: 20px; font-size: 14px; line-height: 1.75; color: rgb(116, 118, 122);\">"+res.getString("passwordReset.ConfirmNewPassword.resete.messageIgnore")+"</td>"
									+"</tr>"
									+"<tr>"
									+"<td style=\"padding-top: 10px;\">"
									+"<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\">"
							+"<tbody>"
								+"<tr>"
									+"<td align=\"center\" bgcolor=\"#1A74BA\" style=\"padding: 8px 20px; box-shadow: rgb(225, 225, 222) 0px 2px 0px;\">"
										+"<a href=\"http://localhost:8080/ITMount/NewPassword?user="+user.getEmail()+"&key="+user.getKey()+"&code="+cal.getTime().getTime()+"\" style=\"display: block; font-family: arial; font-size: 14px; font-weight: bold; text-decoration: none; color: rgb(255, 255, 255); line-height: 1;\" target=\"_blank\">"+res.getString("passwordReset.ResetPassword")+"</a>"
									+"</td>"
								+"</tr>"
							+"</tbody>"
							+"</table>"
							+"</td>"
							+"</tr>"
							+"</tbody>"
							+"</table>"
							+"</td>"
							+"</tr>"
							+"</tbody>"
							+"</table>");
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
