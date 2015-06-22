package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.project.db.model.GroupUser;
import com.epam.project.db.model.User;
import com.epam.project.db.transformer.GroupUserTransformer;
import com.epam.project.db.transformer.UserTransformer;

public class GroupUserDAO {
	private static final String GET_GROUP_USER_BY_USER_AND_GROUP_ID = "SELEct * FROM group_user WHERE user_id = ? AND group_id = ?;";
	private static final String GET_GROUP_USER_BY_ID = "SELECT * FROM group_user WHERE id=?";
	private static final String GET_ALL_GROUP_USER = "select * FROM user WHERE  id IN(SELECT user_id FROM group_user WHERE group_id = ?);";
	private static final String NEW_GROUP_USER = "INSERT INTO group_user (user_id, group_id) value (?, ?);";
	private static final String DELETE_USER_FROM_GROUP = "DELETE FROM group_user WHERE group_id = ? AND user_id = ?;";
	private static final String UPDATE_GROUP_USER = "UPDATE group_user SET user_id = ?, group_id = ?, is_active = ? WHERE id = ?;";
	private static final String GET_ALL_USER_BY_GROUP_ID = "select* from user where user.id in (select user_id from group_user where group_id = ?)";
	private static final String GET_ALL_TEACHER_BY_GROUP_ID = "select* from user where user.id in (select teacher_id from group1 where group1.id = ?)";

	public static GroupUser getGroupUserById(Integer id, Connection connection) {

		ResultSet rs = null;
		GroupUser groupUser = null;
		try {

			PreparedStatement st = connection
					.prepareStatement(GET_GROUP_USER_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			groupUser = GroupUserTransformer.getGroupUser(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groupUser;

	}

	public static List<User> getAllGroupUser(Connection connection,
			Integer group_id) {

		ResultSet rs = null;
		List<User> list = null;
		try {

			PreparedStatement st = connection
					.prepareStatement(GET_ALL_GROUP_USER);
			st.setInt(1, group_id);
			rs = st.executeQuery();
			list = UserTransformer.getAllUsers(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static List<User> getAllUserByGroupId(Connection connection,
			Integer id) {

		ResultSet rs = null;
		List<User> list = null;
		try {

			PreparedStatement st = connection
					.prepareStatement(GET_ALL_USER_BY_GROUP_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			list = UserTransformer.getAllUsers(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void addNewGroupeUser(GroupUser groupUser,
			Connection connection) {

		try {
			PreparedStatement st = connection.prepareStatement(NEW_GROUP_USER);

			st.setInt(1, groupUser.getUserID());
			st.setInt(2, groupUser.getGroupID());

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static User getTeacherByGroupId(Connection connection, Integer id) {

		ResultSet rs = null;
		User user = null;
		try {

			PreparedStatement st = connection
					.prepareStatement(GET_ALL_TEACHER_BY_GROUP_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			user = UserTransformer.getUser(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public static void updateGroupUser(GroupUser groupUser,
			Connection connection) {
		try {

			PreparedStatement st = connection
					.prepareStatement(UPDATE_GROUP_USER);

			st.setInt(1, groupUser.getUserID());
			st.setInt(2, groupUser.getGroupID());
			st.setBoolean(3, groupUser.getIsActive());

			st.setInt(4, groupUser.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteUser(Connection connection, Integer user_id,
			Integer group_id) {
		try {
			PreparedStatement ps = connection
					.prepareStatement(DELETE_USER_FROM_GROUP);
			ps.setInt(1, group_id);
			ps.setInt(2, user_id);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void deleteUsersFromGroup(Connection connection,
			Integer group_id, List<String> users) {
		try {

			connection.setAutoCommit(false);
			PreparedStatement ps = connection
					.prepareStatement("delete from group_user where group_id = ? AND user_id = (select id from user where email = ?);");

			for (String string : users) {

				ps.setInt(1, group_id);
				ps.setString(2, string);

				ps.executeUpdate();

			}

			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addUsersToGroup(Connection connection, Integer group_id,
			List<String> users) {
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			ps = connection
					.prepareStatement("INSERT INTO group_user SET group_id = ?, user_id = (SELECT id  FROM user WHERE email = ?);");

			for (String string : users) {

				ps.setInt(1, group_id);
				ps.setString(2, string);

				ps.executeUpdate();

			}

			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void leaveUsersInGroup(Connection connection,
			Integer group_id, List<String> users) {
		if (users.isEmpty()) {
			return;
		}

		PreparedStatement ps = null;
		String input = "(";
		String separator = "";
		for (String string : users) {
			input += separator;
			input += "?";
			separator = ",";
		}
		input += ")";
		try {

			ps = connection
					.prepareStatement("select id from user where email in "
							+ input);
			List<Integer> users_id = new ArrayList<Integer>();
			for (int i = 0; i < users.size(); i++) {
				ps.setString(i + 1, users.get(i));
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				users_id.add(rs.getInt(1));
			}

			ps = connection.prepareStatement(String.format(
					"delete from group_user "
							+ "WHERE group_id = ? AND user_id not IN " + input,
					input));
			ps.setInt(1, group_id);
			for (int i = 0; i < users.size(); i++) {
				ps.setInt(i + 2, users_id.get(i));
			}

			ps.executeUpdate();

			ps = connection
					.prepareStatement("UPDATE group_user SET is_active = 1 WHERE group_id = ? AND user_id = ?;");
			for (Integer id : users_id) {
				ps.setInt(1, group_id);
				ps.setInt(2, id);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void rebaseUsers(Connection connection, Integer old_group_id,
			Integer new_group_id, List<String> users) {
		PreparedStatement ps = null;
		try {
			ps = connection
					.prepareStatement("UPDATE group_user SET group_id = ? WHERE group_id = ? AND user_id = (SELECT id  FROM user WHERE email = ?);");
			for (String email : users) {
				ps.setInt(1, new_group_id);
				ps.setInt(2, old_group_id);
				ps.setString(3, email);
				ps.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static GroupUser getByGroupAndUserId(Connection connection,
			Integer group_id, Integer user_id) {
		GroupUser association = null;
		PreparedStatement ps = null;
		ResultSet set = null;
		try {
			ps = connection
					.prepareStatement(GET_GROUP_USER_BY_USER_AND_GROUP_ID);
			ps.setInt(1, user_id);
			ps.setInt(2, group_id);
			set = ps.executeQuery();

			association = GroupUserTransformer.getGroupUser(set);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return association;
	}

	public static void setExamDate(Connection connection, Integer group_id,
			Integer user_id, Integer exam_id) {
		PreparedStatement ps = null;
		try {
			ps = connection
					.prepareStatement("UPDATE group_user SET exam_id = ? WHERE group_id = ? AND user_id = ? ;");
			ps.setInt(1, exam_id);
			ps.setInt(2, group_id);
			ps.setInt(3, user_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void leaveGroup(Connection connection, Integer group_id,
			Integer user_id) {
		PreparedStatement statement = null;
		try {
			statement = connection
					.prepareStatement("DELETE FROM group_user WHERE group_id = ? AND user_id = ?;");
			statement.setInt(1, group_id);
			statement.setInt(2, group_id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
