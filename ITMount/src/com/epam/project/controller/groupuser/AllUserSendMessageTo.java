package com.epam.project.controller.groupuser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.project.db.model.Group;
import com.epam.project.db.model.User;
import com.epam.project.db.service.GroupService;
import com.epam.project.db.service.GroupUserService;

public class AllUserSendMessageTo {

	public static List<User> getAllUserSendMessageTo(HttpServletRequest request) {

		HttpSession session = request.getSession();

		List<User> listUsers = new ArrayList<>();
		User user = (User) session.getAttribute("user");

		/*
		 * List of groups on which user study
		 */

		List<Group> listGroup = GroupService.getGroupsUserStudy(user.getId());

		for (Group g : listGroup) {
			System.out.println("List groups1 " + g.toString());
		}

		/*
		 * if group not active it delete
		 */
		for (int i = 0; i < listGroup.size(); i++) {
			if (listGroup.get(i).getIs_active().equals(false)) {
				listGroup.remove(i);
			}
		}

		for (Group g : listGroup) {
			System.out.println("List groups2 " + g.toString());
		}

		for (int i = 0; i < listGroup.size(); i++) {
			List<User> users = GroupUserService.getAllUserByGroupId(listGroup
					.get(i).getId());
			User teacher = GroupUserService.getTeacherByGroupId(listGroup
					.get(i).getId());
			listUsers.add(teacher);
			listUsers.addAll(users);
		}
		
		 Set<User> s= new HashSet<User>();
		  s.addAll(listUsers);         
		  listUsers = new ArrayList<User>();
		  listUsers.addAll(s); 

		for (User us : listUsers) {
			System.out.println(us);
		}

		return listUsers;

	}
}
