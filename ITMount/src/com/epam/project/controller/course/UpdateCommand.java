package com.epam.project.controller.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.epam.project.command.Action;
import com.epam.project.db.model.Course;
import com.epam.project.db.service.CourseService;
import com.epam.project.util.file.UploadFile;

@MultipartConfig
public class UpdateCommand implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Course course = new Course();
		course.setId(Integer.parseInt(request.getParameter("course_id")));
		course.setDescription(request.getParameter("description"));
		course.setIcon(request.getParameter("icon"));
		course.setName(request.getParameter("name"));
	
		Part file = request.getPart("icon");
		UploadFile m = new UploadFile();
		if (file.getSize()>0) {
			
			try{
				if(m.getExtension(file).contains("image")){
					String fileName = m.uploadFile(file, request.getServletContext(),null);
					course.setIcon(fileName);
				}
			}catch(Exception e){
				
				
				response.sendRedirect(request.getHeader("Referer"));
				return;
			}
			
			

		}
		
		
		if (course.isValid()) {

			CourseService.updateCourse(course);
			request.getRequestDispatcher(
					"/CourseServlet?action=show&course_id=" + course.getId())
					.forward(request, response);
			return;
		} else {
			response.sendRedirect(request.getHeader("Referer"));
			return;
		}

	}

	@Override
	public String getName() {

		return "update";
	}

}
