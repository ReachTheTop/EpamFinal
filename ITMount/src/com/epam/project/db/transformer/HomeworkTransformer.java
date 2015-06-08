package com.epam.project.db.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.project.db.model.HomeWork;

public class HomeworkTransformer {

	public static HomeWork getHomeWork(ResultSet rs) {

		HomeWork homeWork = null;

		try {
			while (rs.next()) {
				homeWork = new HomeWork();
				homeWork.setId(rs.getInt(1));
				homeWork.setData(rs.getString(2));
				homeWork.setTask_id(rs.getInt(3));
				homeWork.setUser_id(rs.getInt(4));
				homeWork.setRating(rs.getInt(5));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return homeWork;

	}

	public static List<HomeWork> getAllHomeWork(ResultSet rs) {
		List<HomeWork> list = new ArrayList<HomeWork>();
		HomeWork homeWork = null;

		try {
			while (rs.next()) {
				homeWork = new HomeWork();
				homeWork.setId(rs.getInt(1));
				homeWork.setData(rs.getString(2));
				homeWork.setTask_id(rs.getInt(3));
				homeWork.setUser_id(rs.getInt(4));
				homeWork.setRating(rs.getInt(5));
				list.add(homeWork);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
	
}
