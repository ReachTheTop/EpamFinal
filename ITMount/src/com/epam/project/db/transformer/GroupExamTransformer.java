package com.epam.project.db.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.project.db.model.GroupExamModel;

public class GroupExamTransformer {

	public static List<GroupExamModel> getExams(ResultSet set) {
		List<GroupExamModel> exams = new ArrayList<GroupExamModel>();
		try {
			while (set.next()) {
				exams.add(parseExam(set));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exams;
	}

	public static GroupExamModel getExam(ResultSet set) {
		GroupExamModel exam = null;
		try {
			if (set.next()) {
				exam = parseExam(set);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exam;
	}

	private static GroupExamModel parseExam(ResultSet data) {
		GroupExamModel exam = new GroupExamModel();
		try {
			exam.setId(data.getInt("id"));
			exam.setDescription(data.getString("description"));
			exam.setExam_date(data.getTimestamp("exam_date"));
			exam.setGroup_id(data.getInt("group_id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exam;
	}
}
