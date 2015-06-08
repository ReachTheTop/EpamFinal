package com.epam.project.db.dao;


import com.epam.project.db.model.Course;
import com.epam.project.db.transformer.CourseTransformer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class CourseDAO {

	public static void addCourse(Course course, Connection connection){
		try{
		PreparedStatement st = connection.prepareStatement("INSERT INTO course(name,icon,description,status,date_exam) VALUES(?,?,?,?,?);");
		st.setString(1,course.getName());
		st.setString(2, course.getIcon());
		st.setString(3, course.getDescription());
		st.setString(4, course.getStatus());
		st.setTimestamp(5, new Timestamp(course.getExam_date().getTime()));
		st.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
		
	}
	
	public static void delCourse(Integer id,  Connection connection){
	
		try {
			
			PreparedStatement st = connection.prepareStatement("DELETE FROM course WHERE id=?");
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void updateCourse(Course course, Connection connection){
		try {
			
			PreparedStatement st = connection.prepareStatement("UPDATE course SET name=?, icon=?, description=?, status=?, date_exam=? WHERE id=?");
			
			st.setString(1,course.getName());
			st.setString(2, course.getIcon());
			st.setString(3, course.getDescription());
			st.setString(4, course.getStatus());
			st.setTimestamp(5, new Timestamp(course.getExam_date().getTime()));
			st.setInt(6, course.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Course> getAllCourse(Connection connection){
		ResultSet rs = null;
		List<Course> list = null;
		try {
		
			PreparedStatement st = connection.prepareStatement("SELECT * FROM course");
			rs = st.executeQuery();
			list =CourseTransformer.getAllCourse(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static Course getCourse(Integer id,Connection connection){
		ResultSet rs = null;
		Course course =null;
		try {
			
			PreparedStatement st = connection.prepareStatement("SELECT * FROM course WHERE id=?");
			st.setInt(1, id);
			rs = st.executeQuery();
			course= CourseTransformer.getCourse(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course;
	}
	
	
}
