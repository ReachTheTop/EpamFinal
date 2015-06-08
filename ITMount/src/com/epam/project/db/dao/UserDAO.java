package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.model.User;

public class UserDAO {

	public static final String SQL_INSERT_NEW_USER = "Insert into user (name,midle_name,surname,birthday,password_hash,salt,curriculum_vitae,description,role,is_active,is_confirmed, key1,image,email) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SQL_UPDATE_USER_NAME = "update user set name = ? where user.id = ?";
	public static final String SQL_UPDATE_USER_MIDDLE_NAME = "update user set midle_name = ? where user.id = ?";
	public static final String SQL_UPDATE_USER_SURNAME = "update user set surname = ? where user.id = ?";
	public static final String SQL_UPDATE_USER_BIRTHDAY = "update user set birthday = ? where user.id = ?";
	public static final String SQL_UPDATE_USER_PASSWORD_HASH = "update user set password_hash = ? where user.id = ?";
	public static final String SQL_UPDATE_USER_SALT = "update user set salt = ? where user.id = ?";

	
	public static void updateUserSalt(String salt, int id) {

		PreparedStatement stmt;

		Connection con = DBConnection.getConnection();

		try {
			stmt = con.prepareStatement(SQL_UPDATE_USER_SALT);

			stmt.setString(1,salt);
			stmt.setInt(2, id);

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	public static void updateUserPasswordHash(String passwordHash, int id) {

		PreparedStatement stmt;

		Connection con = DBConnection.getConnection();

		try {
			stmt = con.prepareStatement(SQL_UPDATE_USER_PASSWORD_HASH);

			stmt.setString(1,passwordHash);
			stmt.setInt(2, id);

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	
	public static void updateUserBirthDay(Date userBirthDay, int id) {

		PreparedStatement stmt;

		Connection con = DBConnection.getConnection();

		try {
			stmt = con.prepareStatement(SQL_UPDATE_USER_BIRTHDAY);

			stmt.setDate(1,userBirthDay);
			stmt.setInt(2, id);

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	
	public static void updateUserSurname(String userSurname, int id) {

		PreparedStatement stmt;

		Connection con = DBConnection.getConnection();

		try {
			stmt = con.prepareStatement(SQL_UPDATE_USER_SURNAME);

			stmt.setString(1, userSurname);
			stmt.setInt(2, id);

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	
	public static void updateUserMiddleName(String userMiddleName, int id) {

		PreparedStatement stmt;

		Connection con = DBConnection.getConnection();

		try {
			stmt = con.prepareStatement(SQL_UPDATE_USER_MIDDLE_NAME);

			stmt.setString(1, userMiddleName);
			stmt.setInt(2, id);

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void updateUserName(String userName, int id) {

		PreparedStatement stmt;

		Connection con = DBConnection.getConnection();

		try {
			stmt = con.prepareStatement(SQL_UPDATE_USER_NAME);

			stmt.setString(1, userName);
			stmt.setInt(2, id);

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void insertNewUser(User user) {

		PreparedStatement stmt;

		Connection con = DBConnection.getConnection();

		try {
			stmt = con.prepareStatement(SQL_INSERT_NEW_USER);

			stmt.setString(1, user.getName());
			stmt.setString(2, user.getMiddle_name());
			stmt.setString(3, user.getSurname());
			stmt.setDate(4, new Date(user.getBirtday().getTime()));
			stmt.setString(5, user.getPassword_hash());
			stmt.setString(6, user.getSalt());
			stmt.setString(7, user.getCurriculum_vitae());
			stmt.setString(8, user.getDescription());
			stmt.setString(9, user.getRole());
			stmt.setBoolean(10, user.getIs_active());
			stmt.setBoolean(11, user.getIs_deleted());
			stmt.setString(12, user.getKey());
			stmt.setString(13, user.getImage());
			stmt.setString(14, user.getEmail());

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
