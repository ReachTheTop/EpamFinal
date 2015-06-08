package com.epam.project.db.service;

import java.sql.Date;

import com.epam.project.db.dao.UserDAO;
import com.epam.project.db.model.User;

public class UserService {
	
	public static void updateUserSalt(String salt, int id) {

		UserDAO.updateUserSalt(salt, id);

	}
	
	
	public static void updateUserPasswordHash(String passwordHash, int id) {

		UserDAO.updateUserPasswordHash(passwordHash, id);

	}

	public static void updateUserBirthDay(Date userBirthDay, int id) {

		UserDAO.updateUserBirthDay(userBirthDay, id);

	}

	public static void updateUserSurname(String userSurname, int id) {

		UserDAO.updateUserSurname(userSurname, id);

	}

	public static void updateUserMiddleName(String userMiddleName, int id) {

		UserDAO.updateUserMiddleName(userMiddleName, id);

	}

	public static void updateUserName(String userName, int id) {

		UserDAO.updateUserName(userName, id);

	}

	public static void insertNewUser(User user) {

		UserDAO.insertNewUser(user);

	}

}
