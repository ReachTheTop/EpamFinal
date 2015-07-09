package com.epam.project.controller.course;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONException;
import org.json.JSONObject;

import com.epam.project.command.Action;
import com.epam.project.db.model.Course;
import com.epam.project.db.service.CourseService;
import com.epam.project.util.file.DeleteFile;
import com.epam.project.util.file.UploadFile;

public class UpdateCommand implements Action {

	private String message;
	private String status;
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
		Part file = request.getPart("icon");
		ResourceBundle res = (ResourceBundle) request.getSession().getAttribute("bundle");

			UploadFile m = new UploadFile();
			if (file.getSize() > 0) {

				
					
						String fileName = m.uploadFile(file,
								request.getServletContext(), null);
						DeleteFile.deleteFile(course.getIcon(),
								request.getServletContext());
						course.setIcon(fileName);
					
				

			}
		
		response.setContentType("application/json");
		if (course.isValid()) {
			CourseService.updateCourse(course);
			status = "success";
			message = res.getString("admin.course.toast.success");
			
			
		} else {

			status = "fail";
			if(course.validate().containsKey("icon")){
			message = res.getString("admin.course.toast.error.file");
			}else{
				message = res.getString("admin.course.toast.error");
			}
		}
		try {
			
			response.getWriter().print(new JSONObject().put(status, message));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {

		return "update";
	}

}
