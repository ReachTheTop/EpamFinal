package com.epam.project.db.model;

import java.io.Serializable;
import java.util.Date;

import com.epam.project.db.model.annotation.Column;
import com.epam.project.db.model.annotation.Table;
import com.epam.project.db.model.validator.Validator;

@Table(name = "group")
public class Group extends Validator implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(value = "id")
	private Integer id;

	@Column(value = "course_id")
	private Integer course_id;

	@Column(value = "teacher_id")
	private Integer teacher_id;

	@Column(value = "name")
	private String name;
	
	private Boolean is_active;
	
	@Column(value = "date")
	private Date date;

	public Group() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}

	public Integer getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(Integer teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", course_id=" + course_id + ", teacher_id="
				+ teacher_id + ", name=" + name + ", is_active=" + is_active
				+ ", date=" + date + "]";
	}

	
	
	

}
