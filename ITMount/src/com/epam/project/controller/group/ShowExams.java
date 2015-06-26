package com.epam.project.controller.group;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.controller.EventMessage;
import com.epam.project.controller.task.ValideDate;
import com.epam.project.db.model.Event;
import com.epam.project.db.model.Group;
import com.epam.project.db.model.GroupExamModel;
import com.epam.project.db.model.GroupUser;
import com.epam.project.db.model.HomeWork;
import com.epam.project.db.model.Task;
import com.epam.project.db.model.User;
import com.epam.project.db.service.ContactService;
import com.epam.project.db.service.EventService;
import com.epam.project.db.service.GroupExamService;
import com.epam.project.db.service.GroupService;
import com.epam.project.db.service.GroupUserService;
import com.epam.project.db.service.HomeWorkService;
import com.epam.project.db.service.TaskService;

public class ShowExams implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");

		Integer group_id = null;
		try {
			if (request.getParameter("group_id") != null) {

				group_id = Integer.parseInt(request.getParameter("group_id"));

				Group group = GroupService.getById(group_id);


				if (group == null) {
					response.sendError(404);
					return;
				}


				List<GroupExamModel> exams = GroupExamService.getAll(group_id);
				GroupUser association = null;
				if (user.getRole().equals("student")
						|| user.getRole().equals("applicant")) {


					association = GroupUserService.getByGroupAndUserId(
							group_id, user.getId());

					if (association == null) {
						response.sendError(404);
						return;
					}

				}


				request.setAttribute("user_id", user);


				request.setAttribute("exams", exams);
				request.setAttribute("association", association);

				
				request.setAttribute("group", group);
				request.getRequestDispatcher("/WEB-INF/group2/showExams.jsp")
						.forward(request, response);
				return;
			}
		} catch (NumberFormatException e) {
			response.sendError(404);
		}

	}

	@Override
	public String getName() {

		return "showExams";
	}

}
