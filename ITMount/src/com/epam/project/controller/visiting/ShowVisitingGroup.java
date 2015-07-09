package com.epam.project.controller.visiting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.DayVisit;
import com.epam.project.db.model.Group;
import com.epam.project.db.model.User;
import com.epam.project.db.model.UserVisiting;
import com.epam.project.db.service.GroupService;
import com.epam.project.db.service.GroupUserService;
import com.epam.project.db.service.JournalService;

public class ShowVisitingGroup implements Action {

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

				List<User> usersGroup = GroupUserService
						.getAllUserByGroupId(group_id);

				List<UserVisiting> listUserVisiting = new ArrayList<UserVisiting>();
				UserVisiting userVisiting;

				for (User us : usersGroup) {
					Integer idUser = us.getId();
					List<DayVisit> journal = JournalService.getJournalDayVisit(
							group_id, idUser);
										
					userVisiting = new UserVisiting();
					userVisiting.setDayVisit(journal);
					userVisiting.setUserId(idUser);
					userVisiting.setUserName(us.getName());
					userVisiting.setUserSurname(us.getSurname());

					listUserVisiting.add(userVisiting);
				}

				request.setAttribute("listUserVisit", listUserVisiting);
				request.setAttribute("user_id", user);
				request.setAttribute("group", group);

				request.getRequestDispatcher("/WEB-INF/group/showVisiting.jsp")
						.forward(request, response);
				return;
			}
		} catch (NumberFormatException e) {
			response.sendError(404);
		}

	}

	@Override
	public String getName() {

		return "showVisiting";
	}

}
