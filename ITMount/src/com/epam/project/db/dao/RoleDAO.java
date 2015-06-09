package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.model.Role;
import com.epam.project.db.transformer.RoleTransformer;

public class RoleDAO {
	
	public static final String SQL_UPDATE_ROLE = "UPDATE role SET role=? WHERE id=?";

	public static final String SQL_ADD_NEW_ROLE = "Insert into role (role) value(?)";

	public static final String SQL_GET_ALL_ROLE = "Select* FROM role";
	public static final String SQL_GET_ROLE = "Select* FROM role WHERE id=?";
	
	
	public static Role getRole(Integer id, Connection connection) {

		ResultSet rs = null;
		Role role = null;
		try {

			PreparedStatement st = connection.prepareStatement(SQL_GET_ROLE);
			st.setInt(1, id);
			rs = st.executeQuery();
			role = RoleTransformer.getRole(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return role;

	}

	public static List<Role> getAllRoles(Connection connection) {
		ResultSet rs = null;
		List<Role> list = null;
		try {

			PreparedStatement st = connection
					.prepareStatement(SQL_GET_ALL_ROLE);
			rs = st.executeQuery();
			list = RoleTransformer.getAllRoles(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void addNewRole(Role role, Connection connection) {

		PreparedStatement stmt;

		Connection con = DBConnection.getConnection();

		try {
			stmt = con.prepareStatement(SQL_ADD_NEW_ROLE);

			stmt.setString(1, role.getRole());
			

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	public static void updateRole(Role role, Connection connection) {

		try {

			PreparedStatement stmt = connection
					.prepareStatement(SQL_UPDATE_ROLE);

			stmt.setString(1, role.getRole());
			

			stmt.setInt(2, role.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
