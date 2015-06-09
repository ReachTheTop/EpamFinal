package com.epam.project.db.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.project.db.model.Role;
import com.epam.project.db.model.User;

public class RoleTransformer {
	
	public static Role getRole(ResultSet rs) {

		Role role = null;

		try {
			while (rs.next()) {
				role = new Role();
				role.setId(rs.getInt(1));
				role.setRole(rs.getString(2));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return role;

	}
	
	
	
	public static List<Role> getAllRoles(ResultSet rs) {
		List<Role> list = new ArrayList<Role>();
		Role role = null;

		try {
			
			while (rs.next()) {
				role = new Role();
				role.setId(rs.getInt(1));
				role.setRole(rs.getString(2));
			
				list.add(role);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

}
