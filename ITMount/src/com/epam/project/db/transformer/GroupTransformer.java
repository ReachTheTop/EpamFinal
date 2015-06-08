package com.epam.project.db.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ArrayList<Group> getAllGroups(ResultSet setOfGroups) {
		ArrayList<Group> events = new ArrayList<>();
		Group group = null;
		try {
			while (setOfGroups.next()) {
				group = new Group();
				parse(group, setOfGroups);
				events.add(group);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return events;
	}
}
