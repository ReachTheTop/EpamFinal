package com.epam.project.db.model;

import java.util.Date;

public class GroupExamModel {

	public GroupExamModel() {
		// TODO Auto-generated constructor stub
	}

	private Integer id;
	private String description;
	private Date exam_date;
	private Integer group_id;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExam_date() {
		return exam_date;
	}

	public void setExam_date(Date exam_date) {
		this.exam_date = exam_date;
	}

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

}
