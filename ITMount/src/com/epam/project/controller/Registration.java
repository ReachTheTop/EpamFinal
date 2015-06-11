package com.epam.project.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.project.db.model.User;
import com.epam.project.db.service.UserService;
import com.epam.project.mailer.Mailer;
import com.epam.project.md5.SaltedMD5;

/**
 * Servlet implementation class Registration
 */
@MultipartConfig
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.removeAttribute("errorRegistration");
		session.removeAttribute("name");
		session.removeAttribute("midlename");
		session.removeAttribute("surname");
		session.removeAttribute("email");
		session.removeAttribute("date");
		request.getRequestDispatcher("WEB-INF/page/registration.jsp").forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String midlename = request.getParameter("midlename");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String date = request.getParameter("date");
		
		if(UserService.getUserWhereEmail(email)!=null){
			 session.setAttribute("errorRegistration", " This email used!");
			 session.setAttribute("name", name);
				session.setAttribute("midlename", midlename);
				session.setAttribute("surname", surname);
				session.setAttribute("email", email);
				session.setAttribute("date", date);
			 request.getRequestDispatcher("WEB-INF/page/registration.jsp").forward(request, response);
			 return;
		}
		
		if(name.isEmpty()||midlename.isEmpty()||surname.isEmpty()||email.isEmpty()||password.isEmpty()||date.isEmpty()){
			session.setAttribute("name", name);
			session.setAttribute("midlename", midlename);
			session.setAttribute("surname", surname);
			session.setAttribute("email", email);
			session.setAttribute("date", date);
			session.setAttribute("errorRegistration", " Incorect data!");
			 request.getRequestDispatcher("WEB-INF/page/registration.jsp").forward(request, response);
			 return;
		}else{
			User user = new User();
			user.setName(name);
			user.setMiddle_name(midlename);
			user.setSurname(surname);
			user.setEmail(email);
			user.setRole_id(1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
			try {
				Date d = sdf.parse(request.getParameter("date"));
				user.setBirtday(d);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(request.getPart("photo"));
			Integer key = new Random().nextInt(Integer.MAX_VALUE);
			user.setKey(key.toString());
			try {
				Mailer.sendEmail(email, "Confirm email", "<a href=\"http://localhost:8080/ITMount/confirm?email="+email+"&key="+key+"\">Verificate</a>");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			session.setAttribute("confirmemail", 1);
			session.setAttribute("userkey", key);
			session.setAttribute("useremail", email);
			user.setPassword_hash(SaltedMD5.getPassword(password, email));
			UserService.addNewUser(user);
			request.getRequestDispatcher("WEB-INF/page/login.jsp").forward(request,
					response);
		}

		
		
		
		
		
	}

}
