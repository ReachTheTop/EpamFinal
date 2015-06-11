package com.epam.project.db.model;

public enum CourseStatus {
	
	STATUS_ACTIVE("active"),
	STATUS_OPEN_TO_REGISTRATION("open_to_registration"),
	STATUS_COMPLETED("completed");
	
	 private final String text;
	 
	 private CourseStatus(final String text) {
	        this.text = text;
	    }
	 
	 @Override
	    public String toString() {
	        return text;
	    }


}
