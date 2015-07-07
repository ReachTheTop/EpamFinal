package com.epam.project.db.model;

import java.io.Serializable;

import com.epam.project.db.model.annotation.Column;
import com.epam.project.db.model.annotation.Presence;
import com.epam.project.db.model.annotation.Table;
import com.epam.project.db.model.validator.Validator;

@Table(name="knowlegdebase")
public class KnowledgeBase extends Validator implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(value="id")
	private Integer id;
	
	@Column(value="path")
	@Presence
	private String path;
	
	@Column(value="available")
	private Boolean available;
	
	@Column(value="is_active")
	private Boolean is_active;
	
	@Column(value="course_id")
	private Integer course_id;
	@Column(value="box_id")
	private String box_id;
	@Column(value="box_session")
	private String box_session;
	public KnowledgeBase() {
		// TODO Auto-generated constructor stub
	}
	
	public String getBox_id() {
		return box_id;
	}

	public void setBox_id(String box_id) {
		this.box_id = box_id;
	}

	public String getBox_session() {
		return box_session;
	}

	public void setBox_session(String box_session) {
		this.box_session = box_session;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	public Boolean getIs_active() {
		return is_active;
	}
	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}

	@Override
	public String toString() {
		return "KnowledgeBase [id=" + id + ", path=" + path + ", available="
				+ available + ", is_active=" + is_active + ", course_id="
				+ course_id + ", box_id=" + box_id + ", box_session="
				+ box_session + "]";
	}

	
	
}
