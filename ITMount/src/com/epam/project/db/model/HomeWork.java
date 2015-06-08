package com.epam.project.db.model;

import java.io.Serializable;

import com.epam.project.db.model.annotation.Column;
import com.epam.project.db.model.annotation.Table;
import com.epam.project.db.model.validator.Validator;

@Table(name="homework")
public class HomeWork extends Validator implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(value="id")
	private Integer id;
	
	@Column(value="data")
	private String data;
	
	@Column(value="task_id")
	private Integer task_id;
	
	@Column(value="user_id")
	private Integer user_id;
	
	@Column(value="rating")
	private int rating;
	
	public HomeWork() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Integer getTask_id() {
		return task_id;
	}
	public void setTask_id(Integer task_id) {
		this.task_id = task_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "HomeWork [id=" + id + ", data=" + data + ", task_id=" + task_id
				+ ", user_id=" + user_id + ", rating=" + rating + "]";
	}
	
}
