package com.epam.project.controller.exam;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.epam.project.command.Action;
import com.epam.project.db.connection.DBConnection;

public class CSVList implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow row = sheet.createRow(0);
		
		

		
		Integer exam_id = Integer.parseInt(request.getParameter("exam_id"));
		
		ResourceBundle res = (ResourceBundle) request.getSession().getAttribute("bundle");
		Connection connection = DBConnection.getConnection();
		String query = "SELECT * FROM user AS u LEFT JOIN contact AS c ON u.id = c.user_id WHERE u.id IN (SELECT user_id FROM group_user WHERE exam_id = ?);";
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, exam_id);
			ResultSet data = statement.executeQuery();

			HSSFCell cell = row.createCell(0);
			cell.setCellValue(res.getString("CSVList.surname"));
			
			cell = row.createCell(1);
			cell.setCellValue(res.getString("CSVList.firstname"));
			
			cell = row.createCell(2);
			cell.setCellValue(res.getString("CSVList.lastname"));
			
			cell = row.createCell(3);
			cell.setCellValue(res.getString("CSVList.email"));
			
			cell = row.createCell(4);
			cell.setCellValue(res.getString("CSVList.phone"));
			
			int i = 1;
			while (data.next()) {
				row = sheet.createRow(i);
				
				cell = row.createCell(0);
				cell.setCellValue(data.getString("surname"));
				
				cell = row.createCell(1);
				cell.setCellValue(data.getString("name"));
				
				cell = row.createCell(2);
				cell.setCellValue(data.getString("midle_name"));
				
				cell = row.createCell(3);
				cell.setCellValue(data.getString("email"));
				
				cell = row.createCell(4);
				cell.setCellValue(data.getString("phone"));
				
			}
		
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=filename.xls");
		
		workbook.write(response.getOutputStream()); // Write workbook to response.


	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "toCSV";
	}

}
