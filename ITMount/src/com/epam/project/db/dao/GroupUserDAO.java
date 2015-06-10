package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.epam.project.db.model.Group;
import com.epam.project.db.model.GroupUser;
import com.epam.project.db.transformer.GroupUserTransformer;

public class GroupUserDAO {
	
	private static final String GET_GROUP_USER_BY_ID = "SELECT * FROM group_user WHERE id=?";
	private static final String GET_ALL_GROUP_USER = "SELECT* FROM group_user";
	private static final String NEW_GROUP_USER = "INSERT INTO group_user (user_id, group_id, is_active) value (?, ?, ?);";
	
	private static final String UPDATE_GROUP_USER = "UPDATE group_user SET user_id = ?, group_id = ?, is_active = ? WHERE id = ?;";

	public static GroupUser getGroupUserById(Integer id, Connection connection) {

		ResultSet rs = null;
		GroupUser groupUser = null;
		try {

			PreparedStatement st = connection.prepareStatement(GET_GROUP_USER_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			groupUser = GroupUserTransformer.getGroupUser(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groupUser;

	}
	
	public static List<GroupUser> getAllGroupUser(Connection connection) {

		ResultSet rs = null;
		List<GroupUser> list = null;
		try {

			PreparedStatement st = connection.prepareStatement(GET_ALL_GROUP_USER);
			rs = st.executeQuery();
			list = GroupUserTransformer.getAllGroupsUser(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	
	public static void addNewGroupeUser(GroupUser groupUser, Connection connection) {
		
		try {
			PreparedStatement st = connection.prepareStatement(NEW_GROUP_USER);
			
			st.setInt(1, groupUser.getUserID());
			st.setInt(2, groupUser.getGroupID());
			st.setBoolean(3, groupUser.getIsActive());
			
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void updateGroupUser(GroupUser groupUser, Connection connection){
		try {
			
			PreparedStatement st = connection.prepareStatement(UPDATE_GROUP_USER);

			st.setInt(1, groupUser.getUserID());
			st.setInt(2, groupUser.getGroupID());
			st.setBoolean(3, groupUser.getIsActive());
			
			st.setInt(4, groupUser.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
