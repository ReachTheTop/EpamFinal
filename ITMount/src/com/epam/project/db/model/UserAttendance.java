package com.epam.project.db.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class UserAttendance implements Serializable {
	private static final long serialVersionUID = 1L;
	private User user;
	private TreeMap<Date,Attendance> attendanceQueue;
	public UserAttendance() {
		user = new User();
		attendanceQueue = new TreeMap<Date,Attendance>(); 
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public TreeMap<Date,Attendance> getAttendanceQueue() {
		return attendanceQueue;
	}
	public void setAttendanceQueue(TreeMap<Date,Attendance> attendanceQueue) {
		this.attendanceQueue = attendanceQueue;
	}
	public void addRecord(Attendance attendance){
		attendanceQueue.put(attendance.getDate(), attendance);
	}
	public Integer getQueueSize(){
		return attendanceQueue.size();
	}
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("\n" + user.getName());
		for (Map.Entry<Date,Attendance> attendance : attendanceQueue.entrySet()) {
			builder.append(attendance.getValue());
		}
		return builder.toString();
	}
}