package com.epam.project.db.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.project.db.model.Attendance;
import com.epam.project.db.model.User;
import com.epam.project.db.model.UserAttendance;

public class UserAttendanceTransformer {
	public static List<UserAttendance> getAllRecords(ResultSet rs) {

		/*
		 * SELECT user.id, user.name, user.midle_name ,user.surname,
		 * user.birthday ,user.role_id, user.password_hash
		 * ,user.curriculum_vitae ,user.description, user.is_active,
		 * is_confirmed, user.key1, user.image, user.email, journal.id,
		 * journal.group_id, journal.date, journal.visit, journal.description
		 */
		List<UserAttendance> usrAtt = new ArrayList<UserAttendance>();
		UserAttendance temp;
		User user;
		Attendance att;
		Integer prev = -1;
		try {
			while (rs.next()) {
				att = new Attendance();
				/*
				 * to skip repeated user creation
				 */
				if (prev != rs.getInt(1)) {
					/*
					 * if user isn't in list, create new user, and put it in
					 */
					user = new User();
					temp = new UserAttendance();
					prev = rs.getInt(1);
					user.setId(prev);
					user.setName(rs.getString(2));
					user.setMiddle_name(rs.getString(3));
					user.setSurname(rs.getString(4));

					user.setBirtday(rs.getDate(5));
					user.setRole_id(rs.getInt(6));
					user.setPassword_hash(rs.getString(7));
					user.setCurriculum_vitae(rs.getString(8));
					user.setDescription(rs.getString(9));

					user.setIs_active(rs.getBoolean(10));
					user.setIs_confirmed(rs.getBoolean(11));
					user.setKey(rs.getString(12));
					user.setImage(rs.getString(13));
					user.setEmail(rs.getString(14));
					temp.setUser(user);
					att = new Attendance();
					att.setId(rs.getInt(15));
					att.setDate(rs.getDate(16));
					att.setVisit(rs.getBoolean(17));
					att.setDescription(rs.getString(18));
					temp.addRecord(att);
					usrAtt.add(temp);
				}
				/*
				 * if user was in list, skipping creation, and filling in
				 * attendanceQueue
				 */
				else {
					att = new Attendance();
					att.setId(rs.getInt(15));
					att.setDate(rs.getDate(16));
					att.setVisit(rs.getBoolean(17));
					att.setDescription(rs.getString(18));
					usrAtt.get(usrAtt.size()-1).addRecord(att);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usrAtt;
	}
}
