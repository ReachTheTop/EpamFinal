package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.crypto.spec.PSource;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.model.Group;
import com.epam.project.db.model.User;

public class GroupDAO {
	private static final String NEW_GROUP = "INSERT INTO group1(course_id, teacher_id, name) VALUE (?, ?, ?);";
	private static final String GET_ALL = "SELECT * FROM group1;";
	private static final String GET_BY_ID = "SELECT * FROM group1 WHERE id = ?";
	private static final String UPDATE = "UPDATE group SET course_id = ?, teacher_id = ?, name = ?, is_active = ? WHERE id = ?;";
	private static final String DELETE = "UPDATE group SET is_active = 0 WHERE id = ?;";

	private static final String ADD_USER = "INSERT INTO group_user(user_id, group_id) "
			+ "VALUE (?,(SELECT  id from group1 where course_id = ? AND name = 'Base'));";

	private Connection con;
	private PreparedStatement statement;
	private ResultSet resultSet;

	public void addUser(User user, Integer id) {
		con = DBConnection.getConnection();
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement(ADD_USER);

			statement.setInt(1, user.getId());
			statement.setInt(2, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Integer id) {
		con = DBConnection.getConnection();
		try {
			statement = con.prepareStatement(DELETE);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Group group) {
		con = DBConnection.getConnection();
		try {
			statement = con.prepareStatement(UPDATE);
			statement.setInt(1, group.getCourse_id());
			statement.setInt(2, group.getTeacher_id());
			statement.setString(3, group.getName());
			statement.setBoolean(4, group.getIs_active());
			statement.setInt(5, group.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getById(Integer id) {
		con = DBConnection.getConnection();

		try {
			statement = con.prepareStatement(GET_BY_ID);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	public ResultSet getAll() {
		con = DBConnection.getConnection();
		try {
			statement = con.prepareStatement(GET_ALL);
			resultSet = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
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

	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
