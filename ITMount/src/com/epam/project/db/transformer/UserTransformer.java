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
				user.setMiddle_name(rs.getString(3));
				user.setSurname(rs.getString(4));
				user.setBirtday(rs.getDate(5));				
				user.setPassword_hash(rs.getString(6));
				user.setSalt(rs.getString(7));
				user.setCurriculum_vitae(rs.getString(8));
				user.setDescription(rs.getString(9));
				user.setRole(rs.getString(10));
				user.setIs_active(rs.getBoolean(11));
				user.setIs_confirmed(rs.getBoolean(12));
				user.setKey(rs.getString(13));
				user.setImage(rs.getString(14));
				user.setEmail(rs.getString(15));
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
				user.setMiddle_name(rs.getString(3));
				user.setSurname(rs.getString(4));
				user.setBirtday(rs.getDate(5));				
				user.setPassword_hash(rs.getString(6));
				user.setSalt(rs.getString(7));
				user.setCurriculum_vitae(rs.getString(8));
				user.setDescription(rs.getString(9));
				user.setRole(rs.getString(10));
				user.setIs_active(rs.getBoolean(11));
				user.setIs_confirmed(rs.getBoolean(12));
				user.setKey(rs.getString(13));
				user.setImage(rs.getString(14));
				user.setEmail(rs.getString(15));
			
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

}
