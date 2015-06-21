package com.epam.project.controller.exam;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.connection.DBConnection;

public class CSVList implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer exam_id = Integer.parseInt(request.getParameter("exam_id"));
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition",
				"attachment; filename=\"userDirectory.csv\"");

		Connection connection = DBConnection.getConnection();
		String query = "SELECT * FROM user AS u LEFT JOIN contact AS c ON u.id = c.user_id WHERE u.id IN (SELECT user_id FROM group_user WHERE exam_id = ?);";
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, exam_id);
			ResultSet data = statement.executeQuery();

			response.setContentType("text/csv");
			String reportName = "GenerateCSV_Report_"
					+ System.currentTimeMillis() + ".csv";
			response.setHeader("Content-disposition", "attachment; "
					+ "filename=" + reportName);

			ArrayList<String> rows = new ArrayList<String>();

			rows.add("Surname");
			rows.add(";");
			rows.add("First name");
			rows.add(";");
			rows.add("Last name");
			rows.add(";");
			rows.add("Email");
			rows.add(";");
			rows.add("Phone");
			rows.add("\n");
			while (data.next()) {
				rows.add(data.getString("surname"));
				rows.add(";");
				rows.add(data.getString("name"));
				rows.add(";");
				rows.add(data.getString("midle_name"));
				rows.add(";");
				rows.add(data.getString("email"));
				rows.add(";");
				rows.add(data.getString("phone"));
				rows.add("\n");
			}

			Iterator<String> iter = rows.iterator();
			while (iter.hasNext()) {
				String outputString = (String) iter.next();
				response.getOutputStream().print(outputString);
			}

			response.getOutputStream().flush();

		} catch (Exception e) {

			System.out.println(e.toString());
		}

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "toCSV";
	}

}
