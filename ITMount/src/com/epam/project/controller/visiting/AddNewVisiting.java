package com.epam.project.controller.visiting;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Journal;
import com.epam.project.db.model.User;
import com.epam.project.db.service.GroupUserService;
import com.epam.project.db.service.JournalService;
import com.google.gson.Gson;

public class AddNewVisiting implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int group_id = Integer.parseInt(request.getParameter("group_id"));
		String json = request.getParameter("checkBoxVisit");

		String[] checkedIdUser = new Gson().fromJson(json, String[].class);
				
		Journal journal;
		
		List<User> usersGroup = GroupUserService
				.getAllUserByGroupId(group_id);
		List<Journal> listJournal = new ArrayList<Journal>();
		
		
				
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateVisit = null;

		try {
			
			if(!request.getParameter("dateLesson").isEmpty()){
			 dateVisit = sdf.parse(request.getParameter("dateLesson"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
			
		if( !checkDateIsPresent(dateVisit,group_id) || !checkDateRange(dateVisit) ){
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		
	
		for (int i = 0; i < usersGroup.size(); i++) {
			journal = new Journal();
			journal.setDate(dateVisit);
			journal.setGroupID(group_id);
			journal.setUserID(usersGroup.get(i).getId());
		   
			boolean isPresent = checkUserInPresent(checkedIdUser,usersGroup.get(i).getId());
			if(isPresent == true){
				journal.setVisit(true);
			}else{
				journal.setVisit(false);
			}
			listJournal.add(journal);
			JournalService.addNewJournal(journal);
		}
		
		
		String json1 = new Gson().toJson(listJournal);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json1);
		

	}
	
	private boolean checkDateIsPresent(Date date, int  group_id){
	
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String dateString = df.format(date);
		
		List<Journal> jornal = JournalService.getAllJournalByDate(group_id, dateString);
		
		if(jornal.size() !=0){
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
	
	
	
	private  static  boolean checkUserInPresent(String[] listCheckUser, int userId){
		
		if(listCheckUser == null || listCheckUser.length ==0){
			return false;
		}
		
		for(String idUser : listCheckUser){
			if(Integer.parseInt(idUser) == userId){
				return true;
			}	
		}
		
		return false;
	}

	@Override
	public String getName() {

		return "addVisit";
	}

}
