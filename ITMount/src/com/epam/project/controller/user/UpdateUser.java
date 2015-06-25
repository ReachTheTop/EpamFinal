package com.epam.project.controller.user;

import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONException;
import org.json.JSONObject;

import com.epam.project.command.Action;
import com.epam.project.db.model.Contact;
import com.epam.project.db.model.User;
import com.epam.project.db.service.ContactService;
import com.epam.project.db.service.UserService;
import com.epam.project.util.file.DeleteFile;
import com.epam.project.util.file.UploadFile;
import com.google.gson.Gson;

public class UpdateUser implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		request.setCharacterEncoding("utf-8");
		User current_user = (User) request.getSession().getAttribute("user");

		Contact contacts = ContactService.getByUserId(current_user.getId());
		current_user.setName(request.getParameter("name"));
		current_user.setMiddle_name(request.getParameter("middle_name"));
		current_user.setSurname(request.getParameter("surname"));
		current_user.setDescription(request.getParameter("description"));
		current_user.setEmail(request.getParameter("email"));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date d = sdf.parse(request.getParameter("userBirthday"));
			current_user.setBirtday(d);
			System.out.println(d);
		} catch (ParseException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}

		Part image = request.getPart("image");
		UploadFile m = new UploadFile();
		if (image.getSize() > 0) {
			String fileName = m.uploadFile(image, request.getServletContext(),
					"user_id" + current_user.getId());
			if (current_user.getImage() != null) {
				DeleteFile.deleteFile(current_user.getImage(),
						request.getServletContext());
			}
			current_user.setImage(fileName);
		}
		Part cv = request.getPart("cv");

		if (cv != null) {
			if (cv.getSize() > 0) {

				String fileName = m.uploadFile(cv, request.getServletContext(),
						"user_id" + current_user.getId());
				if (current_user.getCurriculum_vitae() != null) {
					DeleteFile.deleteFile(current_user.getCurriculum_vitae(),
							request.getServletContext());
				}
				current_user.setCurriculum_vitae(fileName);
			}
		}
		contacts.setPhone(request.getParameter("phone"));
		contacts.setSkype(request.getParameter("skype"));

		UserService.updateUser(current_user);
		ContactService.updateContact(contacts);
		session.setAttribute("user", current_user);
		// response.sendRedirect(request.getHeader("Referer"));
		
		Date birthday = current_user.getBirtday();
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		String s = formatter.format(birthday);
		current_user.setBirtdayString(s);
		

		current_user.setContact(contacts);
		String json = new Gson().toJson(current_user);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "update";
	}

}
