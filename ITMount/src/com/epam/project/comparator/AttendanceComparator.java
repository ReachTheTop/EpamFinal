package com.epam.project.comparator;

import java.util.Comparator;

import com.epam.project.db.model.Attendance;

public class AttendanceComparator implements Comparator<Attendance>{

	@Override
	public int compare(Attendance o1, Attendance o2) {
		return (int) (o1.getDate().getTime() - o2.getDate().getTime()) ;
	}

}
