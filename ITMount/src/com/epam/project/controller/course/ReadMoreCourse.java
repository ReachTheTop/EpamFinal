package com.epam.project.controller.course;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.project.command.Action;
import com.epam.project.db.model.Course;
import com.epam.project.db.model.Group;
import com.epam.project.db.model.User;
import com.epam.project.db.service.CourseService;
import com.epam.project.db.service.GroupService;

public class ReadMoreCourse implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Integer course_id = Integer.parseInt(request.getParameter("course_id"));
		request.setAttribute("course",CourseService.getCourse(course_id));
		List<Course> list = CourseService.getAllActiveCourses();
		List<Course> listc = new LinkedList<Course>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user!=null){
		Group groupK = GroupService.getGroupByUserAcsessKnowladgeBase(user.getId(), course_id);
		Group groupR = GroupService.getGroupByUserRegisterOnCourse(user.getId(), course_id);
		
		if(groupR==null && user.getRole().equals("student")){
			request.setAttribute("registationOFF", true);
		}
		 if(groupK!=null){
			 request.setAttribute("knowladeTrue", true);
		 }
		}
		boolean permit=false;
		for (int i = 0; i < list.size()+1; i++) {
			if(i==(list.size())){
				i=-1;
				continue;
			}else{
				if(listc.size()==3||list.size()==1){
					break;
				}
				if(list.get(i).getId()==course_id){
					permit=true;
				}else {
					if(permit){
						listc.add(list.get(i));
					
					
				}
				}
			}
			
			
		}
		
		request.setAttribute("courses",listc);
		
		request.getRequestDispatcher("/WEB-INF/courses/readMore.jsp").forward(
				request, response);
	}
	@Override
	public String getName() {
		return "readMore";
	}
}
