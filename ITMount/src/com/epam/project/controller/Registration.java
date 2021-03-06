package com.epam.project.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;







import com.epam.project.db.model.Contact;
import com.epam.project.db.model.User;
import com.epam.project.db.service.ContactService;
import com.epam.project.db.service.UserService;
import com.epam.project.mailer.Mailer;
import com.epam.project.md5.SaltedMD5;
import com.epam.project.util.file.UploadFile;

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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		ResourceBundle res = (ResourceBundle) request.getSession().getAttribute("bundle");
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String midlename = request.getParameter("midlename");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String date = request.getParameter("date");
		String skype = request.getParameter("skype");
		String tel = request.getParameter("tel");
		
	
		if(UserService.getUserWhereEmail(email)!=null){
			 session.setAttribute("errorRegistration", res.getString("Registration.error.emailUsed"));
			 session.setAttribute("name", name);
				session.setAttribute("midlename", midlename);
				session.setAttribute("surname", surname);
				session.setAttribute("email", email);
				session.setAttribute("date", date);
				session.setAttribute("skype", skype);
				session.setAttribute("tel", tel);
			 request.getRequestDispatcher("WEB-INF/page/registration.jsp").forward(request, response);
			 return;
		}
		
		if(name.isEmpty()||midlename.isEmpty()||surname.isEmpty()||email.isEmpty()||password.isEmpty()||date.isEmpty()){
			session.setAttribute("name", name);
			session.setAttribute("midlename", midlename);
			session.setAttribute("surname", surname);
			session.setAttribute("email", email);
			session.setAttribute("date", date);
			session.setAttribute("skype", skype);
			session.setAttribute("tel", tel);
			session.setAttribute("errorRegistration", " "+res.getString("Registration.error.incorectData"));
			 request.getRequestDispatcher("WEB-INF/page/registration.jsp").forward(request, response);
			 return;
		}else{
			User user = new User();
			user.setName(name);
			user.setMiddle_name(midlename);
			user.setSurname(surname);
			user.setEmail(email);
			user.setRole_id(2);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d = sdf.parse(request.getParameter("date"));
				user.setBirtday(d);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String key = SaltedMD5.getPassword(((Integer)new Random().nextInt(Integer.MAX_VALUE)).toString(), email) ;
			user.setKey(key);
			
			
			
			sendMail(user, res);
			session.setAttribute("confirmemail", 1);
			session.setAttribute("userkey", key);
			session.setAttribute("useremail", email);
			user.setPassword_hash(SaltedMD5.getPassword(password, email));
			
			UserService.addNewUser(user);
			user =UserService.getUserWhereEmail(user.getEmail());
			Part file = request.getPart("photo");
			UploadFile m = new UploadFile();
			if (file.getSize()>0) {
				
				try{
					if(m.getExtension(file).contains("image")){
						String fileName = m.uploadFile(file, getServletContext(),"user_id"+user.getId());
						user.setImage(fileName);
					}
				}catch(Exception e){
					session.setAttribute("errorRegistration", " "+res.getString("Registration.error.incorectPhoto"));
					 request.getRequestDispatcher("WEB-INF/page/registration.jsp").forward(request, response);
					 return;
				}
				
				

			}else {
				user.setImage("photo\\1.jpg");
			}
			
			UserService.updateUser(user);
			Contact contact = new Contact();
			contact.setPhone(tel);
			contact.setSkype(skype);
			contact.setUser_id(user.getId());
			ContactService.addContact(contact);
			
			if(contact.isValid()||user.isValid()){
				
				request.getRequestDispatcher("WEB-INF/page/login.jsp").forward(request,
						response);
			}else{
				ContactService.delContact(ContactService.getByUserId(user.getId()).getId());
				UserService.delUser(user.getId());
				session.setAttribute("name", name);
				session.setAttribute("midlename", midlename);
				session.setAttribute("surname", surname);
				session.setAttribute("email", email);
				session.setAttribute("date", date);
				session.setAttribute("skype", skype);
				session.setAttribute("tel", tel);
				session.setAttribute("errorRegistration", " "+res.getString("Registration.error.incorectData"));
				request.getRequestDispatcher("WEB-INF/page/registration.jsp").forward(request, response);
				return;
			}
			
		}

		
		
		
		
		
	}
	
private void sendMail(final User user, final ResourceBundle res) {
		
		
		
		Thread mailer = new Thread(new Runnable() {
			@Override
			public void run() {
				
					try {
						Mailer.sendEmail(user.getEmail(), res.getString("login.sendemail.subject"), " <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" bgcolor=\"#f2f2f2\">"
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
											+"<a href=\"http://localhost:8080/ITMount/confirm?email="+user.getEmail()+"&key="+user.getKey()+"\" style=\"display: block; font-family: arial; font-size: 14px; font-weight: bold; text-decoration: none; color: rgb(255, 255, 255); line-height: 1;\" target=\"_blank\">"+res.getString("registration.verifyemail")+"</a>"
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
