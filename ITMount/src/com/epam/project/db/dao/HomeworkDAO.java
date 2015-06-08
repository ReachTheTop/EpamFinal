package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.model.HomeWork;
import com.epam.project.db.transformer.HomeworkTransformer;


public class HomeworkDAO {

	private static final String INSERT = "INSERT INTO homework(data,task_id,user_id,rating) VALUES(?,?,?,?);";
	private static final String DELETE = "DELETE FROM Homework WHERE id=?";
	private static final String UPDATE = "UPDATE homework SET data=?, task_id=?, user_id=?, rating=? WHERE id=?";
	private static final String SELECTALL = "SELECT * FROM homework";
	private static final String SELECT = "SELECT * FROM homework WHERE id=?";
	
	public static void addHomework(HomeWork homework, Connection connection){
		try{
		PreparedStatement st = connection.prepareStatement(INSERT);
		st.setString(1,homework.getData());
		st.setInt(2, homework.getTask_id());
		st.setInt(3, homework.getUser_id());
		st.setInt(4, homework.getRating());
		st.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
		
	}
	
	public static void delHomework(Integer id,  Connection connection){
	
		try {
			
			PreparedStatement st = connection.prepareStatement(DELETE);
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void updateHomework(HomeWork homework, Connection connection){
		try {
			
			PreparedStatement st = connection.prepareStatement(UPDATE);
			
			st.setString(1,homework.getData());
			st.setInt(2, homework.getTask_id());
			st.setInt(3, homework.getUser_id());
			st.setInt(4, homework.getRating());
			st.setInt(5, homework.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<HomeWork> getAllHomework(Connection connection){
		ResultSet rs = null;
		List<HomeWork> list = null;
		try {
		
			PreparedStatement st = connection.prepareStatement(SELECTALL);
			rs = st.executeQuery();
			list = HomeworkTransformer.getAllHomeWork(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static HomeWork getHomework(Integer id,Connection connection){
		ResultSet rs = null;
		HomeWork homework =null;
		try {
			
			PreparedStatement st = connection.prepareStatement(SELECT);
			st.setInt(1, id);
			rs = st.executeQuery();
			homework= HomeworkTransformer.getHomeWork(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return homework;
	}
	
	
}
