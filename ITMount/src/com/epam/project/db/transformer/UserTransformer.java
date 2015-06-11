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
				user.setRole_id(rs.getInt(6));
				user.setPassword_hash(rs.getString(7));
				user.setCurriculum_vitae(rs.getString(8));
				user.setDescription(rs.getString(9));

				user.setIs_active(rs.getBoolean(10));
				user.setIs_confirmed(rs.getBoolean(11));
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
			
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

}
