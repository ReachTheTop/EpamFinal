package com.epam.project.db.model;

import java.io.Serializable;
import java.util.Date;

import com.epam.project.db.model.annotation.Column;
import com.epam.project.db.model.annotation.Table;
import com.epam.project.db.model.validator.Validator;

@Table(name = "event")
public class Event extends Validator implements Serializable, Comparable<Event> {

	private static final long serialVersionUID = 1L;

	@Column(value = "id")
	private Integer id;
	
	@Column(value = "name")
	private String nameEvent;
	
	private String typeEvent;

	@Column(value = "description")
	private String description;

	@Column(value = "date")
	private Date date;

	@Column(value = "group_id")
	private Integer group_id;

	@Column(value = "is_active")
	private Boolean is_active;
	
	private String message;

	public Event() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNameEvent() {
		return nameEvent;
	}

	public void setNameEvent(String nameEvent) {
		this.nameEvent = nameEvent;
	}

	public String getTypeEvent() {
		return typeEvent;
	}

	public void setTypeEvent(String typeEvent) {
		this.typeEvent = typeEvent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}
	
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	@Override
	public int compareTo(Event o) {
		
		Event event1 = o;
		
		return date.compareTo(event1.date);
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", nameEvent=" + nameEvent + ", typeEvent="
				+ typeEvent + ", description=" + description + ", date=" + date
				+ ", group_id=" + group_id + ", is_active=" + is_active
				+ ", message=" + message + "]";
	}




	

}
