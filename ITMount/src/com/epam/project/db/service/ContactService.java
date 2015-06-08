package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.dao.ContactDAO;
import com.epam.project.db.model.Contact;

public class ContactService {

	public static void addContact(Contact contact) {
		Connection connection =  DBConnection.getConnection();
		ContactDAO.addConatct(contact, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void delContact(Integer id) {
		Connection connection =  DBConnection.getConnection();
		ContactDAO.delContact(id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateContact(Contact contact) {
		Connection connection =  DBConnection.getConnection();
		ContactDAO.updateContact(contact, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Contact> getAllContacts() {
		Connection connection =  DBConnection.getConnection();
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
		Connection connection =  DBConnection.getConnection();
		Contact contact = ContactDAO.getContact(id, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contact;
	}
	
}
