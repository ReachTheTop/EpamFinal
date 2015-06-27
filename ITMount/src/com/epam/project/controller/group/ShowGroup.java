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

public class ShowGroup implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");

		Integer group_id = null;
		try {
			if (request.getParameter("group_id") != null) {

				group_id = Integer.parseInt(request.getParameter("group_id"));

				Group group = GroupService.getById(group_id);

				List<User> group_users = GroupUserService
						.getAllGroupUser(group_id);
				for(int i=0;i<group_users.size();i++){
					group_users.get(i).setContact(ContactService.getByUserId(group_users.get(i).getId()));
				}
				if (group == null) {
					response.sendError(404);
					return;
				}

				List<Event> events = EventService.getByIdGroup(group_id);
				for(Event event :events){
					EventMessage.updateEventMessage(event);
				}
				Collections.sort(events);

				request.setAttribute("events", events);

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


				/*
				 * All task for user
				 */
				Map<Task, HomeWork> tasks = new LinkedHashMap<Task, HomeWork>();

				List<Task> listTask = ValideDate
						.getTasksAfterValideDate(group_id);

				Collections.sort(listTask);

				for (int i = listTask.size() - 1; i >= 0; i--) {
					HomeWork homeWork;
					homeWork = HomeWorkService.getHomeworkWhereUserTask(
							user.getId(), listTask.get(i).getId());

					tasks.put(listTask.get(i), homeWork);
				}

				request.setAttribute("listtasks", tasks);
				request.setAttribute("user_id", user);


				request.setAttribute("exams", exams);
				request.setAttribute("association", association);

				request.setAttribute("users", group_users);
				request.setAttribute("group", group);
				request.getRequestDispatcher("/WEB-INF/group/show.jsp")
						.forward(request, response);
				return;
			}
		} catch (NumberFormatException e) {
			response.sendError(404);
		}

	}

	@Override
	public String getName() {

		return "show";
	}

}
