package com.epam.project.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Contact;
import com.epam.project.db.model.User;
import com.epam.project.db.service.ContactService;
import com.epam.project.db.service.UserService;

public class UpdateUser implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User current_user = (User) request.getSession().getAttribute("user");
		Contact contacts = ContactService.getByUserId(current_user.getId());
		current_user.setName(request.getParameter("name"));
		current_user.setMiddle_name(request.getParameter("middle_name"));
		current_user.setSurname(request.getParameter("surname"));
		current_user.setDescription(request.getParameter("description"));
		current_user.setEmail(request.getParameter("email"));

		contacts.setPhone(request.getParameter("phone"));
		contacts.setSkype(request.getParameter("skype"));

		UserService.updateUser(current_user);
		ContactService.updateContact(contacts);

		response.sendRedirect(request.getHeader("Referer"));

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "update";
	}

}
