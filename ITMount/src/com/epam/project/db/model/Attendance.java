package com.epam.project.db.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.epam.project.db.model.annotation.Column;
import com.epam.project.db.model.validator.Validator;

public class Attendance extends Validator implements Serializable,Comparable<Attendance> {

	private static final long serialVersionUID = 1L;
	@Column(value = "id")
	private Integer id;
	
	@Column(value = "date")
	private Date date;

	@Column(value = "visit")
	private Boolean visit;
	
	@Column(value = "description")
	private String description;
	
	public Attendance() {
		super();
	}

	public Attendance(Date date, Boolean visit, String description) {
		super();
		this.date = date;
		this.visit = visit;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	public String getSimpleDate (){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YY");
		return dateFormat.format(date);
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
	public int compareTo(Attendance o) {
		return this.date.compareTo(o.date);
	}
	@Override
	public String toString(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return "" + dateFormat.format(this.getDate()) + " " + this.getVisit() + " " + this.getDescription();
	}
}
