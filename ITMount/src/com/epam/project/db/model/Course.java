package com.epam.project.db.model;

import java.io.Serializable;
import java.util.Date;

import com.epam.project.db.model.annotation.Column;
import com.epam.project.db.model.annotation.Format;
import com.epam.project.db.model.annotation.Presence;
import com.epam.project.db.model.annotation.Size;
import com.epam.project.db.model.annotation.Table;
import com.epam.project.db.model.validator.Validator;

@Table(name = "course")
public class Course extends Validator implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(value = "id")
	private Integer id;

	@Column(value = "name")
	@Size(min=1,max=50)
	@Format(format="^[^<^>]*$")
	@Presence()
	private String name;

	@Column(value = "icon")
	@Format(format="png|jpg|jpeg$")
	private String icon;

	@Column(value = "description")
	@Size(min=1,max=700)
	@Presence()
	@Format(format="^[^<^>]*$")
	private String description;
	@Column(value = "is_active")
	private Boolean is_active;


	
	

	public Course() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getStatus() {
		return is_active;
	}

	public void setStatus(Boolean status) {
		this.is_active = status;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", icon=" + icon
				+ ", description=" + description + ", is_active=" + is_active
				+ "]";
	}

	

	
}
