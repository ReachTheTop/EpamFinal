package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.epam.project.db.model.GroupExamModel;
import com.epam.project.db.transformer.GroupExamTransformer;

public class GroupExamDAO {

	private static String NEW_EXAM = "INSERT group_exam SET description = ?, exam_date = ?, group_id = ? ;";
	private static String GET_EXAMS = "SELECT * FROM group_exam WHERE exam_date >= now();";
	private static String UPDATE_EXAM = "UPDATE group_exam SET description = ?, exam_date = ?;";
	private static String GET_BY_GROUP_ID = "SELECT * from group_exam WHERE group_id = ?;";
	private static String GET_BY_ID = "SELECT * FROM group_exam WHERE id = ?;";
	public static void createExam(Connection connection, GroupExamModel exam) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(NEW_EXAM);
			ps.setString(1, exam.getDescription());
			ps.setTimestamp(2, new Timestamp(exam.getExam_date().getTime()));
			ps.setInt(3, exam.getGroup_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<GroupExamModel> getAll(Connection connection) {
		PreparedStatement ps = null;
		List<GroupExamModel> exams = null;
		try {
			ps = connection.prepareStatement(GET_EXAMS);
			exams = GroupExamTransformer.getExams(ps.executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exams;

	}
	
	public static GroupExamModel getByGroupId(Connection connection, Integer id) {
		PreparedStatement ps = null;
		GroupExamModel exam = null;
		try {
			ps = connection.prepareStatement(GET_BY_GROUP_ID);
			ps.setInt(1, id);
			exam = GroupExamTransformer.getExam(ps.executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exam;

	}
	
	public static GroupExamModel getById(Connection connection, Integer id) {
		PreparedStatement ps = null;
		GroupExamModel exam = null;
		try {
			ps = connection.prepareStatement(GET_BY_ID);
			ps.setInt(1, id);
			exam = GroupExamTransformer.getExam(ps.executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exam;

	}
	
	
}
