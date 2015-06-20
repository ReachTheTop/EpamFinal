package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.model.Group;
import com.epam.project.db.model.User;
import com.epam.project.db.transformer.GroupTransformer;

public class GroupDAO {
	private static final String GET_COURSE_ID = "SELECT course_id FROM group1 WHERE id = ?;";
	private static final String NEW_BASE_GROUP = "INSERT INTO group1 SET name = 'Base', confirmed =?, course_id = ?;";
	private static final String ADD_USER = "INSERT INTO group_user(user_id, group_id) "
			+ "VALUE (?,(SELECT  id from group1 where course_id = ? AND confirmed = 0));";
	private static final String CONFIRM_GROUP = "UPDATE group1 SET confirmed = 1 WHERE id = ?;";
	private static final String GET_GROUP_BY_ID = "SELECT * FROM group1 WHERE id=?";
	private static final String GET_ALL_GROUP = "SELECT SQL_CALC_FOUND_ROWS * FROM group1 WHERE is_active =1 AND name REGEXP ? LIMIT ?,?;";
	private static final String CALC_ROWS = "SELECT found_rows();";
	private static final String NEW_GROUP = "INSERT INTO group1 (course_id, teacher_id, name) value (?, ?, ?);";
	private static final String UPDATE = "UPDATE group1 SET course_id = ?, teacher_id = ?, name = ?, is_active = ? WHERE id = ?;";
	private static final String DELETE = "UPDATE group1 SET is_active = 0 WHERE id = ?;";

	private static final String GET_GROUPS_USER_STUDY = "Select* from group1 where group1.id in"
			+ "(select group_id from group_user where group_user.id in"
			+ " (select group_user.id from group_user where user_id = ?))";

	private Connection con;
	private PreparedStatement statement;

	public static void delete(Integer id, Connection con) {
		con = DBConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(DELETE);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addUser(Connection connection, User user, Integer id) {

		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(ADD_USER);

			statement.setInt(1, user.getId());
			statement.setInt(2, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Group getGroupById(Integer id, Connection connection) {

		ResultSet rs = null;
		Group group = null;
		try {

			PreparedStatement st = connection.prepareStatement(GET_GROUP_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			group = GroupTransformer.getGroup(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return group;

	}

	public static GroupCorteg getAll(Connection connection, String token,
			Integer page) {

		String request = "SELECT SQL_CALC_FOUND_ROWS * FROM group1 AS g "
				+ "LEFT JOIN course AS c ON c.id = g.course_id "
				+ "LEFT JOIN user AS u ON u.id = g.teacher_id "
				+ "WHERE g.is_active = 1 AND ( g.name " + "REGEXP ? OR c.name "
				+ "REGEXP ? OR u.surname " + "REGEXP ? ) LIMIT ?,?;";
		String pattern = String.format(".*%s.*", token);
		ResultSet rs = null;
		GroupCorteg groups = new GroupCorteg();

		try {
			connection.setAutoCommit(false);
			PreparedStatement st = connection.prepareStatement(request);
			st.setString(1, pattern);
			st.setString(2, pattern);
			st.setString(3, pattern);
			st.setInt(4, page * 10);
			st.setInt(5, 10);
			rs = st.executeQuery();
			groups.setGroups(GroupTransformer.getAllGroups(rs));

			st = connection.prepareStatement(CALC_ROWS);
			rs = st.executeQuery();
			rs.next();

			groups.setAmount(rs.getInt(1));
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return groups;
	}

	public static List<Group> getGroupsUserStudy(Connection connection,
			Integer id) {

		ResultSet rs = null;
		List<Group> list = null;
		try {

			PreparedStatement st = connection
					.prepareStatement(GET_GROUPS_USER_STUDY);
			st.setInt(1, id);
			rs = st.executeQuery();
			list = GroupTransformer.getAllGroups(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static Integer addNewGroupe(Group group, Connection connection) {
		Integer group_id = null;
		try {
			PreparedStatement st = connection.prepareStatement(NEW_GROUP,
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, group.getCourse_id());
			st.setInt(2, group.getTeacher_id());
			st.setString(3, group.getName());
			/*
			 * st.setBoolean(4, group.getIs_active()); st.setTimestamp(5, new
			 * Timestamp(group.getDateExam().getTime()));
			 */
			st.executeUpdate();

			ResultSet resultSet = st.getGeneratedKeys();
			resultSet.next();
			group_id = resultSet.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return group_id;
	}

	public static void updateGroup(Group group, Connection connection) {
		try {

			PreparedStatement st = connection.prepareStatement(UPDATE);

			st.setInt(1, group.getCourse_id());
			st.setInt(2, group.getTeacher_id());
			st.setString(3, group.getName());
			st.setBoolean(4, group.getIs_active());

			st.setInt(5, group.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void newGroup(Group group) {
		con = DBConnection.getConnection();
		try {
			statement = con.prepareStatement(NEW_GROUP,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, group.getCourse_id());
			statement.setInt(2, group.getTeacher_id());
			statement.setString(3, group.getName());

			statement.executeUpdate();

			ResultSet set = statement.getGeneratedKeys();
			set.next();
			group.setId(set.getInt(1));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void confirmGroup(Connection connection, Integer group_id) {
		try {
			connection.setAutoCommit(false);
			PreparedStatement ps = connection.prepareStatement(CONFIRM_GROUP);
			ps.setInt(1, group_id);
			ps.executeUpdate();

			ps = connection.prepareStatement(GET_COURSE_ID);
			ps.setInt(1, group_id);
			ResultSet res = ps.executeQuery();
			res.next();
			Integer course_id = res.getInt(1);
			ps = connection.prepareStatement(NEW_BASE_GROUP);
			ps.setBoolean(1, false);
			ps.setInt(2, course_id);

			ps.executeUpdate();
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ResultSet getByTeacher(Connection connection, Integer id) {
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = connection
					.prepareStatement("SELECT * FROM group1 WHERE teacher_id = ?;");
			ps.setInt(1, id);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultSet;
	}
}
