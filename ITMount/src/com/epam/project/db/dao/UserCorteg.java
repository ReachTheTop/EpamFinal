package com.epam.project.db.dao;

import java.util.List;

import com.epam.project.db.model.User;

public class UserCorteg {

	private List<User> users;
	private Integer amount;
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	
}
