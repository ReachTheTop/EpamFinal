package com.epam.project.db.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.project.db.model.User;

public class UserTransformer {
	
	public static User getUser(ResultSet rs) {

		User user = null;

		try {
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setMiddleName(rs.getString(3));
				user.setSurName(rs.getString(4));
				user.setBirtday(rs.getDate(5));
				user.setRoleID(rs.getInt(6));
				user.setPasswordHash(rs.getString(7));
				user.setCurriculumVitae(rs.getString(8));
				user.setDescription(rs.getString(9));
				user.setIsActive(rs.getBoolean(10));
				user.setIsConfirmed(rs.getBoolean(11));
				user.setKey(rs.getString(12));
				user.setImage(rs.getString(13));
				user.setEmail(rs.getString(14));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;

	}
	
	
	
	public static List<User> getAllUsers(ResultSet rs) {
		List<User> list = new ArrayList<User>();
		User user = null;

		try {
			
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setMiddleName(rs.getString(3));
				user.setSurName(rs.getString(4));
				user.setBirtday(rs.getDate(5));
				user.setRoleID(rs.getInt(6));
				user.setPasswordHash(rs.getString(7));
				user.setCurriculumVitae(rs.getString(8));
				user.setDescription(rs.getString(9));
				user.setIsActive(rs.getBoolean(10));
				user.setIsConfirmed(rs.getBoolean(11));
				user.setKey(rs.getString(12));
				user.setImage(rs.getString(13));
				user.setEmail(rs.getString(14));
			
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

}
