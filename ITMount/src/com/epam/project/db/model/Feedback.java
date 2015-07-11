package com.epam.project.db.model;

import java.io.Serializable;
import java.util.Date;

import com.epam.project.db.model.annotation.Column;
import com.epam.project.db.model.annotation.Format;
import com.epam.project.db.model.annotation.Presence;
import com.epam.project.db.model.annotation.Size;
import com.epam.project.db.model.annotation.Table;
import com.epam.project.db.model.validator.Validator;

@Table(name = "feedback")
public class Feedback extends Validator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(value = "id")
	private Integer id;
	@Column(value = "name")
	@Presence
	@Size(min=1,max=30)
	@Format(format="^[^\\d^\\s^!^(^)^#^@^#^$^*^?^+^-^<^>^.^,^/^|^%]*$")
	private String name;
	@Column(value = "email")
	@Presence
	@Format(format="^[\\w_.-]+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")
	private String email;
	@Column(value = "type")
	@Presence
	private String type;
	@Column(value = "content")
	@Presence
	@Size(min=1,max=1900)
	private String content;
	@Column(value = "timeMessage")
	private Date timeMessage;
	@Column(value = "read")
	private Boolean read;
	@Column(value = "active")
	private Boolean active;
	@Column(value = "important")
	private Boolean important;
	@Column(value = "send")
	private Boolean send;
	private String description;
	private String time;
	
	public Boolean getSend() {
		return send;
	}
	public void setSend(Boolean send) {
		this.send = send;
	}
	public Boolean getImportant() {
		return important;
	}
	public void setImportant(Boolean important) {
		this.important = important;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTimeMessage() {
		return timeMessage;
	}
	public void setTimeMessage(Date timeMessage) {
		this.timeMessage = timeMessage;
	}
	public Boolean getRead() {
		return read;
	}
	public void setRead(Boolean read) {
		this.read = read;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "Feedback [id=" + id + ", name=" + name + ", email=" + email
				+ ", type=" + type + ", content=" + content + ", timeMessage="
				+ timeMessage + ", read=" + read + ", active=" + active
				+ ", description=" + description + ", time=" + time + "]";
	}
	
	
	
	
}
