package com.epam.project.controller.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.epam.project.command.Action;
import com.epam.project.db.model.Course;
import com.epam.project.db.service.CourseService;
import com.epam.project.util.file.DeleteFile;
import com.epam.project.util.file.UploadFile;

public class UpdateCommand implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String desc = request.getParameter("description");
		Course course = CourseService.getCourse(Integer.parseInt(request
				.getParameter("course_id")));

		course.setDescription(request.getParameter("description"));
		course.setName(request.getParameter("name"));
		if (request.getParameter("icon") != null) {
			course.setIcon(request.getParameter("icon"));

			Part file = request.getPart("icon");

			UploadFile m = new UploadFile();
			if (file.getSize() > 0) {

				try {
					if (m.getExtension(file).contains("image")) {
						String fileName = m.uploadFile(file,
								request.getServletContext(), null);
						DeleteFile.deleteFile(course.getIcon(),
								request.getServletContext());
						course.setIcon(fileName);
					}
				} catch (Exception e) {

					response.sendRedirect(request.getHeader("Referer"));
					return;
				}

			}
		}
		response.setContentType("application/json");
		if (course.isValid()) {

			CourseService.updateCourse(course);
			/*request.getRequestDispatcher(
					"/CourseServlet?action=readMore&course_id="
							+ course.getId()).forward(request, response);*/
			
		} else {
			/*response.sendRedirect(request.getHeader("Referer"));*/
			
		}

	}

	@Override
	public String getName() {

		return "update";
	}

}
