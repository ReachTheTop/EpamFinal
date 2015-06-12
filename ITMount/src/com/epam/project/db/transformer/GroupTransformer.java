package com.epam.project.db.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.project.db.model.Contact;
import com.epam.project.db.model.Event;
import com.epam.project.db.model.Group;

public class GroupTransformer {
	
	public static Group getGroup(ResultSet data) {
		Group group = null;

		try {
			if (data.next()) {
				group = new Group();
				parse(group, data);
			} else {
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return group;
	}

	private static void parse(Group group, ResultSet data) {
		try {

			group.setName(data.getString("name"));
			group.setTeacher_id(data.getInt("teacher_id"));
			group.setCourse_id(data.getInt("course_id"));
			group.setId(data.getInt("id"));
			group.setIs_active(data.getBoolean("is_active"));
			group.setDateExam(data.getDate("date_exam"));
			group.setIsConfirmed(data.getBoolean("confirmed"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static List<Group> getAllGroups(ResultSet setOfGroups) {
		
		List<Group> list = new ArrayList<Group>();
		Group group = null;

		try {
			while (setOfGroups.next()) {
				
				group = new Group();
				group.setId(setOfGroups.getInt(1));
				group.setCourse_id(setOfGroups.getInt(2));
				group.setTeacher_id(setOfGroups.getInt(3));
				group.setName(setOfGroups.getString(4));
				group.setIs_active(setOfGroups.getBoolean(5));
				group.setDateExam(setOfGroups.getDate(6));
				group.setIsConfirmed(setOfGroups.getBoolean("confirmed"));
				list.add(group);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
		
	}
}
