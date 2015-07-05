package com.epam.project.controler.statistic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.epam.project.db.model.Course;
import com.epam.project.db.service.CourseService;
import com.google.gson.Gson;

/**
 * Servlet implementation class GroupExamServlet
 */
@WebServlet("/StatisticServlet")
public class StatisticServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

	public StatisticServlet() {
		super();
		
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		System.out.println("get");
		
	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		
		List<CourseStudent> list1 = CourseService.getCountStudentCourses();
		
		List<Course> listCourses = CourseService.getAllActiveCourses();
		int idFirstCourse = listCourses.get(0).getId();
		List<CourseComplited> list2 = CourseService.getCountStudentCoursesComplited(idFirstCourse);
		
		
		Map<String,List> map = new HashMap<String, List>();
		map.put("firstList", list1);
		map.put("secondList", list2);
		map.put("thirdList",listCourses);
	
		String json = new Gson().toJson(map);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		
	}

}



