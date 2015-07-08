package com.epam.project.db.model;

import java.io.Serializable;
import java.util.List;

import com.epam.project.db.model.validator.Validator;

public class UserVisiting extends Validator implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	
	private String userName;
	
	private String userSurname;
	
	public List<DayVisit> dayVisit;
	
	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public List<DayVisit> getDayVisit() {
		return dayVisit;
	}

	public void setDayVisit(List<DayVisit> dayVisit) {
		this.dayVisit = dayVisit;
	}

	@Override
	public String toString() {
		return "UserVisiting [userId=" + userId + ", userName=" + userName
				+ ", userSurname=" + userSurname + ", dayVisit=" + dayVisit
				+ "]";
	}




	
	

}
