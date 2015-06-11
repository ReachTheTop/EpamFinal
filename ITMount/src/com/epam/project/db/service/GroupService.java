package com.epam.project.db.service;

import java.util.List;

import com.epam.project.db.dao.GroupDAO;
import com.epam.project.db.model.Group;
import com.epam.project.db.model.User;
import com.epam.project.db.transformer.GroupTransformer;

public class GroupService {

	public static void newGroup(Group group) {
		GroupDAO groupBridge = new GroupDAO();

		groupBridge.newGroup(group);
		groupBridge.close();

	}

	public static void addUserToGroup(User user, Integer id) {
		GroupDAO groupBridge = new GroupDAO();

		groupBridge.addUser(user, id);
		groupBridge.close();
	}

	public static Group getById(Integer id) {
		GroupDAO groupBridge = new GroupDAO();
		Group group = null;
		group = GroupTransformer.getGroup(groupBridge.getById(id));
		groupBridge.close();
		group.setTeacher(UserService.getUser(group.getTeacher_id()));
		group.setCourse(CourseService.getCourse(group.getCourse_id()));
		return group;
	}

	public static List<Group> getAll() {
		List<Group> groups = null;
		GroupDAO groupBridge = new GroupDAO();
		groups = GroupTransformer.getAllGroups(groupBridge.getAll());
		groupBridge.close();
		for (Group group : groups) {
			group.setTeacher(UserService.getUser(group.getTeacher_id()));
			group.setCourse(CourseService.getCourse(group.getCourse_id()));

		}

		return groups;
	}

	public static void update(Group group) {
		GroupDAO groupBridge = new GroupDAO();
		groupBridge.update(group);
		groupBridge.close();
	}

	public static void deleteGroup(Integer id) {
		GroupDAO groupBridge = new GroupDAO();

		groupBridge.delete(id);
		groupBridge.close();
	}
}
