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

import com.epam.project.command.Action;
import com.epam.project.db.model.KnowledgeBase;
import com.epam.project.db.service.KnowledgeBaseService;

public class KnowledgeIndex implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer course_id =Integer.parseInt( (String) request.getParameter("course_id"));
		request.setAttribute("course_id", course_id);
		Map<String, KnowledgeBase> map = new LinkedHashMap<String, KnowledgeBase>();
		List<KnowledgeBase> list = KnowledgeBaseService.getAllKnowledgeBaseForCourse(course_id);
		for (KnowledgeBase knowledgeBase : list) {
			Pattern pattern = Pattern.compile("[0-9a-z]{1,5}$");
			 Matcher matcher = pattern.matcher(knowledgeBase.getPath());
			 while (matcher.find()) {
			map.put( matcher.group(), knowledgeBase);
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
