package com.epam.project.db.model;

import java.io.Serializable;

import com.epam.project.db.model.annotation.Column;
import com.epam.project.db.model.annotation.Table;
import com.epam.project.db.model.validator.Validator;

@Table(name = "group_user")
public class GroupUser extends Validator implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(value = "id")
	private Integer id;

	@Column(value = "user_id")
	private Integer userID;

	@Column(value = "group_id")
	private Integer groupID;

	@Column(value = "is_active")
	private Boolean isActive;

	public GroupUser() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getGroupID() {
		return groupID;
	}

	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}