package com.epam.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.project.db.model.Course;
import com.epam.project.db.service.CourseService;



/**
 * Servlet implementation class Home
 */

public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(Home.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		   List<Course> course = CourseService.getAllActiveCourses();
		 Set<Course> set = new LinkedHashSet<>();
		   Random rand = new Random();
		 
				
					 while(course.size()>0){
						 if(set.size()==3||(course.size()==2&&set.size()==2)||(course.size()==1&&set.size()==1)){
								break;
							}else{
								set.add(course.get(rand.nextInt(course.size())));
							}	
							
						}
		
			  
		
		request.setAttribute("course", set);
		request.getRequestDispatcher("WEB-INF/page/index.jsp").forward(request,
				response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
