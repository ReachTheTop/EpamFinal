package com.epam.project.db.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.epam.project.db.model.Event;

public class EventTransformer {
	public static Event getEvent(ResultSet data) {
		Event event = null;

		try {
			if (data.next()) {
				event = new Event();
				parse(event, data);
			} else {
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return event;
	}

	private static void parse(Event event, ResultSet data) {
		try {
			event.setNameEvent(data.getString("name"));
			event.setTypeEvent(data.getString("type"));
			event.setDate(data.getTimestamp("date"));
			event.setDescription(data.getString("description"));
			event.setGroup_id(data.getInt("group_id"));
			event.setId(data.getInt("id"));
			event.setIs_active(data.getBoolean("is_active"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ArrayList<Event> getAllEvents(ResultSet setOfEvents) {
		ArrayList<Event> events = new ArrayList<Event>();
		Event event = null;
		try {
			while (setOfEvents.next()) {
				event = new Event();
				parse(event, setOfEvents);
				events.add(event);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return events;
	}
}
