package com.epam.project.db.service;

import java.util.List;

import com.epam.project.db.model.GroupUser;
import com.epam.project.db.model.Journal;


public class TestDB {

	public static void main(String[] args) {
		
	//	GroupUser groupeUser = GroupUserService.getById(1);
		
		/*List<Journal> list = JournalService.getAllJournals(); 
		
		for(Journal gu : list){
			System.out.println(gu);
		}
	 */
	
		Journal journal = JournalService.getJournal(1);
		System.out.println(journal);
	//	journal.setDescription("ваіиави");
		
		//JournalService.updateJournal(journal);
		
		
		
	}

}
