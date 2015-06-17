package com.epam.project.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		try {
			
			Mailer.sendEmail(""+session.getAttribute("useremail"), "Confirm email", "<a href=\"http://localhost:8080/ITMount/confirm?email="+session.getAttribute("useremail")+"&key="+session.getAttribute("userkey")+"\">Verificate</a>");
	
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("WEB-INF/page/login.jsp").forward(request,
				response);
	}



}
