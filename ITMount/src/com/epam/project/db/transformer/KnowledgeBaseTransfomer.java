package com.epam.project.db.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.project.db.model.KnowledgeBase;

public class KnowledgeBaseTransfomer {

	public static KnowledgeBase getKnowledgeBase(ResultSet rs) {

		KnowledgeBase kBase = null;

		try {
			while (rs.next()) {
				kBase = new KnowledgeBase();
				kBase.setId(rs.getInt(1));
				kBase.setPath(rs.getString(2));
				kBase.setAvailable(rs.getBoolean(3));
				kBase.setIs_active(rs.getBoolean(4));
				kBase.setCourse_id(rs.getInt(5));
				kBase.setBox_id(rs.getString("box_id"));
				kBase.setBox_session(rs.getString("box_session"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kBase;

	}

	public static List<KnowledgeBase> getAllKnowledgeBase(ResultSet rs) {
		List<KnowledgeBase> list = new ArrayList<KnowledgeBase>();
		KnowledgeBase kBase = null;

		try {
			while (rs.next()) {
				kBase = new KnowledgeBase();
				kBase.setId(rs.getInt(1));
				kBase.setPath(rs.getString(2));
				kBase.setAvailable(rs.getBoolean(3));
				kBase.setIs_active(rs.getBoolean(4));
				kBase.setCourse_id(rs.getInt(5));
				kBase.setBox_id(rs.getString("box_id"));
				kBase.setBox_session(rs.getString("box_session"));
				list.add(kBase);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
}
