package com.epam.project.db.model;

import java.util.Date;

public class DayVisit {
	
	private Integer idJournal;
	
	private Date dayLesson;
	
	private String dayLessonString;
	
	private boolean present;
	
	

	public Integer getIdJournal() {
		return idJournal;
	}

	public void setIdJournal(Integer idJournal) {
		this.idJournal = idJournal;
	}

	public Date getDayLesson() {
		return dayLesson;
	}

	public void setDayLesson(Date dayLesson) {
		this.dayLesson = dayLesson;
	}

	public String getDayLessonString() {
		return dayLessonString;
	}

	public void setDayLessonString(String dayLessonString) {
		this.dayLessonString = dayLessonString;
	}

	public boolean isPresent() {
		return present;
	}

	public void setPresent(boolean isPresent) {
		this.present = isPresent;
	}

	@Override
	public String toString() {
		return "DayVisit [idJournal=" + idJournal + ", dayLesson=" + dayLesson
				+ ", dayLessonString=" + dayLessonString + ", present="
				+ present + "]";
	}

	

	
	
	

}
