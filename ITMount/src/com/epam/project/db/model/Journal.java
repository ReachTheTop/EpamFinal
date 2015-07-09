package com.epam.project.db.model;

import java.io.Serializable;


import java.util.Date;

import com.epam.project.db.model.annotation.Column;
import com.epam.project.db.model.annotation.Table;
import com.epam.project.db.model.validator.Validator;

@Table(name = "journal")
public class Journal extends Validator implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(value = "id")
	private Integer id;

	@Column(value = "group_id")
	private Integer groupID;

	@Column(value = "user_id")
	private Integer userID;

	@Column(value = "date")
	private Date date;

	@Column(value = "visit")
	private Boolean visit;

	@Column(value = "description")
	private String description;

	public Journal() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGroupID() {
		return groupID;
	}

	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getVisit() {
		return visit;
	}

	public void setVisit(Boolean visit) {
		this.visit = visit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Journal [id=" + id + ", groupID=" + groupID + ", userID="
				+ userID + ", date=" + date + ", visit=" + visit
				+ ", description=" + description + "]";
	}

}