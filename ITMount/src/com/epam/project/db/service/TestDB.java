package com.epam.project.db.service;

import java.util.List;

import com.epam.project.db.model.Role;



public class TestDB {

	public static void main(String[] args) {
		
		
		Role role = RoleService.getRole(4);
		
		
		role.setRole("nub");
	
		RoleService.addNewRole(role);
		
	}

}
