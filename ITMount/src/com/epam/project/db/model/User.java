package com.epam.project.db.model;

import java.io.Serializable;
import java.util.Date;

import com.epam.project.db.model.annotation.Column;
import com.epam.project.db.model.annotation.Table;
import com.epam.project.db.model.annotation.Unique;
import com.epam.project.db.model.validator.Validator;

@Table(name = "user")
public class User extends Validator implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(value = "id")
	private Integer id;

	@Column(value = "name")
	private String name;

	@Column(value = "midle_name")
	private String middleName;

	@Column(value = "surname")
	private String surName;

	@Column(value = "birthday")
	private Date birtday;
	
	@Column(value = "role_id")
	private Integer roleID;

	@Column(value = "password_hash")
	private String passwordHash;


	@Column(value = "curriculum_vitae")
	private String curriculumVitae;

	@Column(value = "description")
	private String description;


	@Column(value = "is_active")
	private Boolean isActive;

	@Column(value = "is_confirmed")
	private Boolean isConfirmed;

	@Column(value = "key1")
	private String key;

	@Column(value = "image")
	private String image;

	@Column(value = "email")
	@Unique
	private String email;

	public User() {
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

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middle_name) {
		this.middleName = middle_name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surname) {
		this.surName = surname;
	}

	public Date getBirtday() {
		return birtday;
	}

	public void setBirtday(Date birtday) {
		this.birtday = birtday;
	}

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer role_id) {
		this.roleID = role_id;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String password_hash) {
		this.passwordHash = password_hash;
	}

	public String getCurriculumVitae() {
		return curriculumVitae;
	}

	public void setCurriculumVitae(String curriculum_vitae) {
		this.curriculumVitae = curriculum_vitae;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean is_active) {
		this.isActive = is_active;
	}

	public Boolean getIsConfirmed() {
		return isConfirmed;
	}

	public void setIsConfirmed(Boolean is_confirmed) {
		this.isConfirmed = is_confirmed;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", middle_name="
				+ middleName + ", surname=" + surName + ", birtday=" + birtday
				+ ", role_id=" + roleID + ", password_hash=" + passwordHash
				+ ", curriculum_vitae=" + curriculumVitae + ", description="
				+ description + ", is_active=" + isActive + ", is_confirmed="
				+ isConfirmed + ", key=" + key + ", image=" + image
				+ ", email=" + email + "]";
	}

	

}
