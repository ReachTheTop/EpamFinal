package com.epam.project.controller.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.epam.project.command.Action;
import com.epam.project.db.model.Course;
import com.epam.project.db.service.CourseService;
import com.epam.project.util.file.UploadFile;

public class CreateCommand implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Course course = new Course();
		course.setName(request.getParameter("name"));
		course.setDescription(request.getParameter("description"));
		
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
			course.setId(CourseService.addCourse(course));
			response.sendRedirect("CourseServlet?action=readMore&course_id="+course.getId());
			return;
		} else {
			response.sendRedirect(request.getHeader("Referer"));
			return;
		}

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "create";
	}

}
