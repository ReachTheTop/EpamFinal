package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.model.Event;

public class EventDAO {
	private static final String NEW_EVENT = ""
			+ "INSERT INTO event (description, date, group_id) "
			+ "VALUES (?,?,?);";
	private static final String GET_ALL = "SELECT * FROM event;";

	private static final String DELETE = "" + "UPDATE event "
			+ "SET is_active = 0 " + "WHERE id = ?";
	private static final String UPDATE = "" + "UPDATE event "
			+ "SET description =? , date = ?, group_id = ?, is_active = ? "
			+ "WHERE id = ?;";

	private static final String GET_BY_ID = "SELECT * FROM event WHERE id = ?;";

	private PreparedStatement statement;
	private Connection con;
	private ResultSet resultSet;

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

	public void update(Event event) {
		con = DBConnection.getConnection();
		try {
			statement = con.prepareStatement(UPDATE);
			statement.setString(1, event.getDescription());
			statement.setTimestamp(2, new Timestamp(event.getDate().getTime()));
			statement.setInt(3, event.getGroup_id());
			statement.setBoolean(4, event.getIs_active());
			statement.setInt(5, event.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Integer event_id) {
		con = DBConnection.getConnection();
		try {
			statement = con.prepareStatement(DELETE);
			statement.setInt(1, event_id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	public void newEvent(Event event) {
		con = DBConnection.getConnection();
		try {
			statement = con.prepareStatement(NEW_EVENT);
			statement.setString(1, event.getDescription());
			statement.setTimestamp(2, new Timestamp(event.getDate().getTime()));
			statement.setInt(3, event.getGroup_id());
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
