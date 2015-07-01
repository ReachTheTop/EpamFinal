package com.epam.project.controller;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.project.db.model.User;
import com.epam.project.mailer.Mailer;

/**
 * Servlet implementation class SendConfirm
 */

public class SendConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendConfirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		ResourceBundle res = (ResourceBundle) session.getAttribute("bundle");
		sendMail(session, res);
		request.getRequestDispatcher("WEB-INF/page/login.jsp").forward(request,
				response);
	}

	private void sendMail(final HttpSession session, final ResourceBundle res) {
		
		Thread mailer = new Thread(new Runnable() {
			@Override
			public void run() {
				
					try {
						Mailer.sendEmail(""+session.getAttribute("useremail"), res.getString("login.sendemail.subject"), " <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" bgcolor=\"#f2f2f2\">"
								+"<tbody>"
									+"<tr>"
										+"<td valign=\"top\" style=\"padding: 30px 10px;\">"
											+"<table width=\"580\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">"
								+"<tbody>"
								
										+"</tr>"
										+"<tr>"
										+"<td style=\"padding-bottom: 20px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: rgb(231, 232, 231); font-family: arial; font-size: 16px; font-weight: bold; color: rgb(70, 70, 70);\">"+res.getString("login.sendemail.messageHeder")+"</td>"
										+"</tr>"
										+"<tr>"
										+"<td style=\"padding-top: 20px; font-size: 14px; line-height: 1.75; color: rgb(116, 118, 122);\">"+res.getString("login.sendemail.message")+"</td>"
										+"</tr>"
										+"<tr>"
										+"<td style=\"padding-top: 10px;\">"
										+"<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\">"
								+"<tbody>"
									+"<tr>"
										+"<td align=\"center\" bgcolor=\"#1A74BA\" style=\"padding: 8px 20px; box-shadow: rgb(225, 225, 222) 0px 2px 0px;\">"
											+"<a href=\"http://localhost:8080/ITMount/confirm?email="+session.getAttribute("useremail")+"&key="+session.getAttribute("userkey")+"\" style=\"display: block; font-family: arial; font-size: 14px; font-weight: bold; text-decoration: none; color: rgb(255, 255, 255); line-height: 1;\" target=\"_blank\">"+res.getString("registration.verifyemail")+"</a>"
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
