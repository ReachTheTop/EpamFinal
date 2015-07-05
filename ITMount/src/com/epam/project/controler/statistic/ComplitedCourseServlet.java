package com.epam.project.controler.statistic;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.db.service.CourseService;
import com.google.gson.Gson;


@WebServlet("/ComplitedCourseServlet")
public class ComplitedCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ComplitedCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("dvsdvsdv");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int idCourse = Integer.parseInt(request.getParameter("idCourse"));
		List<CourseComplited> list2 = CourseService.getCountStudentCoursesComplited(idCourse);
	
		String json = new Gson().toJson(list2);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

		
	}

}
