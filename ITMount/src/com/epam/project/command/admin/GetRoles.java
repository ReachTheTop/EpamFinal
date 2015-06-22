package com.epam.project.command.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.connection.DBConnection;
import com.google.gson.Gson;

public class GetRoles implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet data = null;
		List<Role> roles = new ArrayList<Role>();
		try {
			statement = connection.prepareStatement("SELECT * from role;");
			data = statement.executeQuery();
			Role role = null;
			while (data.next()) {
				role = new Role();
				role.setId(data.getInt(1));
				role.setRole(data.getString(2));
				roles.add(role);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		Gson json = new Gson();
		response.setContentType("application/json");
		response.getWriter().write(json.toJson(roles));

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "roles";
	}

}
