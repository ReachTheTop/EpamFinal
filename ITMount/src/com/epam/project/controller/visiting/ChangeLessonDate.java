package com.epam.project.controller.visiting;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Journal;
import com.epam.project.db.service.JournalService;

public class ChangeLessonDate implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int group_id = Integer.parseInt(request.getParameter("group_id"));
		String newDate = request.getParameter("newDateLesson");
		String oldDate = request.getParameter("oldLessonDate");

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date newLessonDate = null;

		if (request.getParameter("newDateLesson").isEmpty()) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		} else {

			try {
				newLessonDate = sdf.parse(newDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}

		}

		if (checkDateIsPresent(newDate, group_id) == false) {

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}

		if (checkDateRange(newLessonDate) == false) {

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		
		List<Journal> listJournal = JournalService.getAllJournalByDate(
				group_id, oldDate);
		for (Journal journal : listJournal) {
			journal.setDate(newLessonDate);
			JournalService.updateJournal(journal);
		}
	}

	private boolean checkDateIsPresent(String date, int group_id) {

		List<Journal> jornal = JournalService.getAllJournalByDate(group_id,
				date);

		if (jornal.size() != 0) {
			return false;
		}

		return true;

	}

	private boolean checkDateRange(Date deadline) {

		boolean dateCorrect = true;

		Calendar cal = Calendar.getInstance();
		cal.setTime(deadline);

		int year = cal.get(Calendar.YEAR);

		if (year < 1970 || year > 2030) {
			dateCorrect = false;
		}

		return dateCorrect;
	}

	@Override
	public String getName() {

		return "changeLessonDate";
	}

}
