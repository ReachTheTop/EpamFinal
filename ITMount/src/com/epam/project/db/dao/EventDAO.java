package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.model.Event;

public class EventDAO {
	
	
	private static final String NEW_EVENT = ""
			+ "INSERT INTO event (name,type,description, date, group_id) "
			+ "VALUES (?,?,?,?,?);";
	
	private static final String UPDATE = "" + "UPDATE event "
			+ "SET  name=?, type=?, description =? , date = ?"
			+ "WHERE id = ?;";
	
	
	
	
	
	private static final String GET_ALL = "SELECT * FROM event where is_active =1;";

	private static final String DELETE = "" + "UPDATE event "
			+ "SET is_active = 0 " + "WHERE id = ?";
	

	private static final String GET_BY_ID = "SELECT * FROM event WHERE id = ?;";
	
	private static final String GET_BY_ID_GROUP = "SELECT * FROM EVENT WHERE group_id=? AND is_active =1 ORDER BY date  DESC;";
	
	
	private static final String COMPLETELY_REMOVE_BY_NAME = "DELETE from event WHERE name = ?;";

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
	
	public ResultSet getByIdGroup(Integer group_id) {
		con = DBConnection.getConnection();
		try {
			statement = con.prepareStatement(GET_BY_ID_GROUP);
			statement.setInt(1, group_id);
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
			statement.setString(1, event.getNameEvent());
			statement.setString(2, event.getTypeEvent());
			statement.setString(3, event.getDescription());
			statement.setTimestamp(4, new Timestamp(event.getDate().getTime()));			
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
	
	public void completelyRemove(String name) {
		con = DBConnection.getConnection();
		try {
			statement = con.prepareStatement(COMPLETELY_REMOVE_BY_NAME);
			statement.setString(1, name);
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

	public Integer newEvent(Event event) {
		con = DBConnection.getConnection();
		Integer event_id = null;
		try {
			statement = con.prepareStatement(NEW_EVENT,
					Statement.RETURN_GENERATED_KEYS );
			statement.setString(1, event.getNameEvent());
			statement.setString(2, event.getTypeEvent());
			statement.setString(3, event.getDescription());
			statement.setTimestamp(4, new Timestamp(event.getDate().getTime()));
			statement.setInt(5, event.getGroup_id());
			
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			resultSet.next();
			event_id = resultSet.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return event_id;

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
