package com.epam.project.db.model;

import java.io.Serializable;

import com.epam.project.db.model.annotation.Column;
import com.epam.project.db.model.annotation.Table;
import com.epam.project.db.model.validator.Validator;


@Table(name = "role")
public class Role extends Validator implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(value = "id")
	private Integer id;
	
	
	@Column(value = "role")
	private String role;


	public Role() {
		super();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + "]";
	}
	
	
	

}
