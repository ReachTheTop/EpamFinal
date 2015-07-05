package com.epam.project.db.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import com.epam.project.db.model.Feedback;


public class FeedbackTransformer {

	public static Feedback getFeedback(ResultSet rs) {

		 Feedback feedback = null;

		try {
			while (rs.next()) {
				feedback = new Feedback();
				feedback.setId(rs.getInt("id"));
				feedback.setName(rs.getString("name"));
				feedback.setEmail(rs.getString("email"));
				feedback.setContent(rs.getString("content"));
				feedback.setType(rs.getString("type"));
				feedback.setRead(rs.getBoolean("read"));
				feedback.setActive(rs.getBoolean("active"));
				feedback.setSend(rs.getBoolean("send"));
				String description = rs.getString("content");
				if(description.length()>20){
					feedback.setDescription(description.substring(0, 20));
				}else{
					feedback.setDescription(description);
				}
				feedback.setTime(getTime(rs.getTimestamp("timeMessage")));	
				Date time = rs.getTimestamp("timeMessage");
				SimpleDateFormat format = new SimpleDateFormat("M/d/yyyy H:mm");
				
			
		feedback.setTimeMessage(new Date(format.format(time)));
				feedback.setImportant(rs.getBoolean("important"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return feedback;
	}
	
	public static List<Feedback> getAllFeedbacks(ResultSet rs) {
		List<Feedback> list = new ArrayList<Feedback>();
		

		 Feedback feedback = null;

			try {
				while (rs.next()) {
					feedback = new Feedback();
					feedback.setId(rs.getInt("id"));
					feedback.setName(rs.getString("name"));
					feedback.setEmail(rs.getString("email"));
					feedback.setContent(rs.getString("content"));
					feedback.setType(rs.getString("type"));
					feedback.setRead(rs.getBoolean("read"));
					feedback.setActive(rs.getBoolean("active"));
					String description = rs.getString("content").replaceAll("</?\\w+((\\s+\\w+(\\s*=\\s*(?:\".*?\"|'.*?'|[^'\">\\s]+))?)+\\s*|\\s*)/?>", "");
					
					if(description.length()>20){
				
						feedback.setDescription(description.substring(0,20));
					}else{
						feedback.setDescription(description);
					}
					Date time = rs.getTimestamp("timeMessage");
							SimpleDateFormat format = new SimpleDateFormat("M/d/yyyy H:mm");
							
						
					feedback.setTimeMessage(new Date(format.format(time)));
					feedback.setTime(getTime(rs.getTimestamp("timeMessage")));	
					feedback.setImportant(rs.getBoolean("important"));
					feedback.setSend(rs.getBoolean("send"));
					list.add(feedback);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;

	}
	
	private static String getTime(Date timeMessage){
		String time = null;
		SimpleDateFormat format;
		Date nowTime = new Date();
		
		int year = nowTime.getYear()-timeMessage.getYear();
		if(year==0){
			int month = nowTime.getMonth()-timeMessage.getMonth();
			if(month==0){
				int day = nowTime.getDay()-timeMessage.getDay();
				if(day==0){
					format = new SimpleDateFormat("H:mm");
					time= format.format(timeMessage);
				}else{
					format = new SimpleDateFormat("d-MM");
					time= format.format(timeMessage);
				}
			}else{
				format = new SimpleDateFormat("M-d");
				time= format.format(timeMessage);
			}
		}else{
			format = new SimpleDateFormat("yyyy-M-d");
			time= format.format(timeMessage);
		}
		return time;
	}
}
