package com.epam.project.db.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.project.db.model.Contact;
import com.epam.project.db.model.Group;
import com.epam.project.db.model.GroupUser;

public class GroupUserTransformer {
	
	public static GroupUser getGroupUser (ResultSet rs) {
		
		GroupUser groupUser = null;

		try {
			while (rs.next()) {
				groupUser = new GroupUser();
				
				groupUser.setId(rs.getInt(1));
				groupUser.setUserID(rs.getInt(2));
				groupUser.setGroupID(rs.getInt(3));
				groupUser.setIsActive(rs.getBoolean(4));
				groupUser.setExam_id(rs.getInt(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groupUser;

	}
	
public static List<GroupUser> getAllGroupsUser(ResultSet rs) {
		
		List<GroupUser> list = new ArrayList<GroupUser>();
		GroupUser groupUser = null;

		try {
			while (rs.next()) {
				
				groupUser = new GroupUser();
				
				groupUser.setId(rs.getInt(1));
				groupUser.setUserID(rs.getInt(2));
				groupUser.setGroupID(rs.getInt(3));
				groupUser.setIsActive(rs.getBoolean(4));
				groupUser.setExam_id(rs.getInt(5));
				list.add(groupUser);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
		
	}


}
