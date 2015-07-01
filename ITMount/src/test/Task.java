package test;

import java.io.Serializable;
import java.util.Date;

import com.epam.project.db.model.annotation.Column;
import com.epam.project.db.model.annotation.Format;
import com.epam.project.db.model.annotation.Presence;
import com.epam.project.db.model.annotation.Size;
import com.epam.project.db.model.annotation.Table;
import com.epam.project.db.model.validator.Validator;

@Table(name = "task")
public class Task extends Validator implements Serializable, Comparable<Task> {

	private static final long serialVersionUID = 1L;

	@Column(value = "id")
	private Integer id;

	@Column(value = "name")
	@Presence
	@Size(min=1,max=40)
	@Format(format="^[^<^>]*$")
	private String name;

	@Column(value = "description")
	@Presence
	@Size(min=1,max=250)
	@Format(format="^[^<^>]*$")
	private String description;

	@Column(value = "deadline")
	@Presence
	private Date deadline;

	@Column(value = "available")
	private Boolean available;

	@Column(value = "file")
	@Presence
	private String file;

	@Column(value = "is_active")
	private Boolean is_active;

	@Column(value = "group_id")
	private Integer groupID;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Integer getGroupID() {
		return groupID;
	}

	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}
	
	
	@Override
	public int compareTo(Task o) {
		
		Task task1 = o;
		
		return deadline.compareTo(task1.deadline);
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", description="
				+ description + ", deadline=" + deadline + ", available="
				+ available + ", file=" + file + ", is_active=" + is_active
				+ ", groupID=" + groupID + "]";
	}


	
	
}
