package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.GroupCorteg;
import com.epam.project.db.dao.GroupDAO;
import com.epam.project.db.model.Group;
import com.epam.project.db.model.User;
import com.epam.project.db.transformer.GroupTransformer;

public class GroupService {

	public static Integer addUserToGroup(User user, Integer id) {
		Connection connection = DBConnection.getConnection();
		Integer group_id = GroupDAO.addUser(connection, user, id);
		closeConnection(connection);
		return group_id;
	}

	public static void deleteGroup(Integer id) {

		Connection connection = DBConnection.getConnection();
		GroupDAO.delete(id, connection);
		closeConnection(connection);
	}

	public static Group getById(Integer id) {
		Connection connection = DBConnection.getConnection();
		Group group = null;
		group = GroupDAO.getGroupById(id, connection);

		group.setTeacher(UserService.getUser(group.getTeacher_id()));
		group.setCourse(CourseService.getCourse(group.getCourse_id()));
		closeConnection(connection);
		return group;
	}

	public static GroupCorteg getAll(String token, Integer page) {
		GroupCorteg groups = null;
		Connection connection = DBConnection.getConnection();
		groups = GroupDAO.getAll(connection, token, page);

		for (Group group : groups.getGroups()) {
			group.setTeacher(UserService.getUser(group.getTeacher_id()));
			group.setCourse(CourseService.getCourse(group.getCourse_id()));

		}
		closeConnection(connection);
		return groups;
	}

	public static List<Group> getGroupsUserStudy(Integer id) {
		List<Group> groups = null;
		Connection connection = DBConnection.getConnection();
		groups = GroupDAO.getGroupsUserStudy(connection, id);

		for (Group group : groups) {
			group.setTeacher(UserService.getUser(group.getTeacher_id()));
			group.setCourse(CourseService.getCourse(group.getCourse_id()));

		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groups;
	}

	public static Integer newGroup(Group group) {

		Connection connection = DBConnection.getConnection();
		Integer group_id = GroupDAO.addNewGroupe(group, connection);
		closeConnection(connection);
		return group_id;
	}

	public static void updateGroup(Group group) {
		Connection connection = DBConnection.getConnection();
		GroupDAO.updateGroup(group, connection);
		closeConnection(connection);

	}

	public static void confirmGroup(Integer group_id) {
		Connection connection = DBConnection.getConnection();
		GroupDAO.confirmGroup(connection, group_id);
		closeConnection(connection);
	}

	private static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Group> getByTeacher(Integer id) {
		Connection connection = DBConnection.getConnection();
		List<Group> groups = null;
		groups = GroupTransformer.getAllGroups(GroupDAO.getByTeacher(
				connection, id));
		for (Group group : groups) {
			group.setTeacher(UserService.getUser(group.getTeacher_id()));
			group.setCourse(CourseService.getCourse(group.getCourse_id()));

		}
		closeConnection(connection);
		return groups;
	}

	public static Group getGroupByTeacherAndCourse(Integer teacher,
			Integer course) {
		Connection connection = DBConnection.getConnection();
		Group group = null;
		group = GroupDAO
				.getGroupByTeacherAndCourse(teacher, course, connection);
		if (group != null) {
			group.setTeacher(UserService.getUser(group.getTeacher_id()));
			group.setCourse(CourseService.getCourse(group.getCourse_id()));
		}

		closeConnection(connection);
		return group;
	}
	public static Group getGroupByUserRegisterOnCourse(Integer user_id,
			Integer course) {
		Connection connection = DBConnection.getConnection();
		Group group = null;
		group = GroupDAO.getGroupByUserRegisterOnCourse(user_id, course, connection);
		if(group!=null){
			group.setTeacher(UserService.getUser(group.getTeacher_id()));
			group.setCourse(CourseService.getCourse(group.getCourse_id()));
		}
		
		closeConnection(connection);
		return group;
	}
	public static Group getGroupByUserAcsessKnowladgeBase(Integer user_id,
			Integer course) {
		Connection connection = DBConnection.getConnection();
		Group group = null;
		group = GroupDAO.getGroupByUserAcsessKnowladgeBase(user_id, course, connection);
		if(group!=null){
			group.setTeacher(UserService.getUser(group.getTeacher_id()));
			group.setCourse(CourseService.getCourse(group.getCourse_id()));
		}
		
		closeConnection(connection);
		return group;
	}
}
