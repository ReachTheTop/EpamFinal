package com.epam.project.db.service;

import java.util.List;

import com.epam.project.db.dao.GroupDAO;
import com.epam.project.db.model.Group;
import com.epam.project.db.transformer.GroupTransformer;

public class GroupService {

	public void newGroup(Group group) {
		GroupDAO groupBridge = new GroupDAO();

		groupBridge.newGroup(group);
		groupBridge.close();

	}

	public Group getById(Integer id) {
		GroupDAO groupBridge = new GroupDAO();
		Group group = null;
		group = GroupTransformer.getGroup(groupBridge.getById(id));
		groupBridge.close();
		return group;
	}

	public List<Group> getAll() {
		List<Group> groups = null;
		GroupDAO groupBridge = new GroupDAO();
		groups = GroupTransformer.getAllGroups(groupBridge.getAll());
		groupBridge.close();

		return groups;
	}

	public void update(Group group) {
		GroupDAO groupBridge = new GroupDAO();
		groupBridge.update(group);
		groupBridge.close();
	}

	public void deleteGroup(Integer id) {
		GroupDAO groupBridge = new GroupDAO();

		groupBridge.delete(id);
		groupBridge.close();
	}
}
