package com.epam.project.db.service;

import java.util.ArrayList;
import java.util.List;

import com.epam.project.db.dao.EventDAO;
import com.epam.project.db.model.Event;
import com.epam.project.db.transformer.EventTransformer;

public class EventService {

	public static void newEvent(Event event) {
		EventDAO eventBridge = new EventDAO();

		eventBridge.newEvent(event);

		eventBridge.close();
	}
	
	public static List<Event> getByIdGroup(Integer group_id) {
		EventDAO eventBridge = new EventDAO();
		List<Event> result = new ArrayList<>();

		result = EventTransformer.getAllEvents(eventBridge.getByIdGroup(group_id));

		eventBridge.close();
		return result;
	}
	

	public static void update(Event event) {
		EventDAO eventBridge = new EventDAO();

		eventBridge.update(event);
		eventBridge.close();
	}

	public static void delete(Integer event_id) {
		EventDAO eventBrodge = new EventDAO();

		eventBrodge.delete(event_id);
		eventBrodge.close();

	}

	public static List<Event> getAll() {
		EventDAO eventBridge = new EventDAO();
		List<Event> result = new ArrayList<>();

		result = EventTransformer.getAllEvents(eventBridge.getAll());

		eventBridge.close();
		return result;
	}

	public static Event getById(Integer event_id) {
		EventDAO eventBridge = new EventDAO();
		Event result = null;
		result = EventTransformer.getEvent(eventBridge.getById(event_id));
		eventBridge.close();
		return result;
	}
}
