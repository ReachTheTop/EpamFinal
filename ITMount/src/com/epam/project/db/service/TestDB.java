package com.epam.project.db.service;

import java.util.List;

import com.epam.project.db.model.GroupUser;


public class TestDB {

	public static void main(String[] args) {
		
	//	GroupUser groupeUser = GroupUserService.getById(1);
		
	/*	List<GroupUser> list = GroupUserService.getAllGroupUSer(); 
		
		for(GroupUser gu : list){
			System.out.println(gu);
		}
	 */
	
		GroupUser groupeUser = GroupUserService.getById(3);
		
		groupeUser.setUserID(4);
		groupeUser.setGroupID(4);
		groupeUser.setIsActive(false);
		
		GroupUserService.updateGroupUser(groupeUser);
		
	}

}
