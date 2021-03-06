package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.ContactDAO;
import com.epam.project.db.model.Contact;

public class ContactService {

	public static void addContact(Contact contact) {
		Connection connection = DBConnection.getConnection();
		ContactDAO.addContact(contact, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void delContact(Integer id) {
		Connection connection = DBConnection.getConnection();
		ContactDAO.delContact(id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateContact(Contact contact) {
		Connection connection = DBConnection.getConnection();
		ContactDAO.updateContact(contact, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Contact> getAllContacts() {
		Connection connection = DBConnection.getConnection();
		List<Contact> list = ContactDAO.getAllContact(connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static Contact getContact(Integer id) {
		Connection connection = DBConnection.getConnection();
		Contact contact = ContactDAO.getContact(id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contact;
	}

	public static Contact getByUserId(Integer user_id) {
		Contact contact = null;
		Connection connection = DBConnection.getConnection();
		contact = ContactDAO.getByUserId(connection, user_id);
		closeConnection(connection);
		return contact;
	}

	private static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
