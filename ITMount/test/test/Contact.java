package test;

import java.io.Serializable;

import com.epam.project.db.model.annotation.Column;
import com.epam.project.db.model.annotation.Format;
import com.epam.project.db.model.annotation.Presence;
import com.epam.project.db.model.annotation.Table;
import com.epam.project.db.model.validator.Validator;

@Table(name = "contact")
public class Contact extends Validator implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(value = "id")
	private Integer id;

	@Column(value = "skype")
	@Format(format="^[a-zA-ZА-Яа-я0-9_-]{3,15}$")
	private String skype;

	@Column(value = "phone")
	@Format(format="^\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*$")
	private String phone;

	@Column(value = "user_id")
	@Presence
	private Integer user_id;

	public Contact() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", skype=" + skype + ", phone=" + phone
				+ ", user_id=" + user_id + "]";
	}
	

}
