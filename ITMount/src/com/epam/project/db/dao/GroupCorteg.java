package com.epam.project.db.dao;

import java.util.List;

import com.epam.project.db.model.Group;

public class GroupCorteg {

	private List<Group> groups;
	private Integer amount;
	public List<Group> getGroups() {
		return groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	
}
