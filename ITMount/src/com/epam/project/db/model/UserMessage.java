package com.epam.project.db.model;

import java.io.Serializable;

import com.epam.project.db.model.annotation.Column;
import com.epam.project.db.model.annotation.Table;
import com.epam.project.db.model.validator.Validator;

@Table(name = "user_message")
public class UserMessage extends Validator implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(value = "id")
	private Integer id;
	
	@Column(value = "message_id")
	private Integer message_id;	
	
	@Column(value = "is_deleted")
	private Boolean is_deleted;
	
	@Column(value = "is_readed")
	private Boolean is_readed;
	
	@Column(value = "receiver_id")
	private Integer receiver_id;
	
	
	
	public UserMessage() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMessage_id() {
		return message_id;
	}

	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}

	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public Boolean getIs_readed() {
		return is_readed;
	}

	public void setIs_readed(Boolean is_readed) {
		this.is_readed = is_readed;
	}

	public Integer getReceiver_id() {
		return receiver_id;
	}

	public void setReceiver_id(Integer receiver_id) {
		this.receiver_id = receiver_id;
	}	
	
	@Override
	public String toString() {
		return "UserMessage [id=" + id + ", message_id=" + message_id + ", is_deleted=" + is_deleted
				+ ", is_readed=" + is_readed + ", receiver_id=" + receiver_id + "]";
	}

}
