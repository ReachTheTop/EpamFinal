package com.epam.project.controller.knowledgebase;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.filters.CorsFilter;

import com.epam.project.command.Action;
import com.epam.project.db.model.Group;
import com.epam.project.db.model.KnowledgeBase;
import com.epam.project.db.model.User;
import com.epam.project.db.service.CourseService;
import com.epam.project.db.service.GroupService;
import com.epam.project.db.service.KnowledgeBaseService;

public class KnowledgeIndex implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sessio = request.getSession();
		Integer course_id =Integer.parseInt( (String) request.getParameter("course_id"));
		request.setAttribute("course_id", course_id);
		Map<KnowledgeBase, String> map = new LinkedHashMap<KnowledgeBase, String>();
		List<KnowledgeBase> list =null;
		
		User user = (User) sessio.getAttribute("user");
		
		if(user!=null&&user.getRole().equals("lecturer")){
			
			Group group= GroupService.getGroupByTeacherAndCourse(user.getId(), course_id);
			if(group != null){
				
				request.setAttribute("is_lecturer", true);
				 list = KnowledgeBaseService.getAllKnowledgeBaseForCourse(course_id);
			}else{
				list = KnowledgeBaseService.getAllKnowledgeBaseForCourseByActive(course_id);
				
			}
		}else{
			list = KnowledgeBaseService.getAllKnowledgeBaseForCourseByActive(course_id);
			
		}
		
		for (KnowledgeBase knowledgeBase : list) {
			Pattern pattern = Pattern.compile("[0-9a-z]{1,5}$");
			 Matcher matcher = pattern.matcher(knowledgeBase.getPath());
			 while (matcher.find()) {
			map.put(knowledgeBase,matcher.group());
			 }
		}
		
		request.setAttribute("base",map
				);
		request.getRequestDispatcher("/WEB-INF/knowledge_base/index.jsp")
				.forward(request, response);

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "index";
	}

}
