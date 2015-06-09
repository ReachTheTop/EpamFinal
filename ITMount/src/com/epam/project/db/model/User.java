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
	private String middle_name;

	@Column(value = "surname")
	private String surname;

	@Column(value = "birthday")
	private Date birtday;

	@Column(value = "password_hash")
	private String password_hash;

	@Column(value = "salt")
	private String salt;

	@Column(value = "curriculum_vitae")
	private String curriculum_vitae;

	@Column(value = "description")
	private String description;

	@Column(value = "role")
	private String role;

	@Column(value = "is_active")
	private Boolean is_active;

	@Column(value = "is_confirmed")
	private Boolean is_confirmed;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirtday() {
		return birtday;
	}

	public void setBirtday(Date birtday) {
		this.birtday = birtday;
	}

	public String getPassword_hash() {
		return password_hash;
	}

	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getCurriculum_vitae() {
		return curriculum_vitae;
	}

	public void setCurriculum_vitae(String curriculum_vitae) {
		this.curriculum_vitae = curriculum_vitae;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public Boolean getIs_confirmed() {
		return is_confirmed;

	}

	public void setIs_confirmed(Boolean is_deleted) {
		this.is_confirmed = is_confirmed;

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

}
