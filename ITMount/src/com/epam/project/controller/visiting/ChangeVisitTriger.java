package com.epam.project.controller.visiting;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Journal;
import com.epam.project.db.service.JournalService;
import com.google.gson.Gson;

public class ChangeVisitTriger implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int journalId = Integer.parseInt(request.getParameter("jornal_id"));

		Journal journal = JournalService.getJournal(journalId);

		boolean visit = journal.getVisit();
		if (visit == true) {
			journal.setVisit(false);
		} else {
			journal.setVisit(true);
		}
		
		JournalService.updateJournal(journal);
		
		Gson gson = new Gson();
		response.setContentType("application/json");
		response.getWriter().write(gson.toJson(journal));
	}

	@Override
	public String getName() {

		return "changeVisit";
	}

}
