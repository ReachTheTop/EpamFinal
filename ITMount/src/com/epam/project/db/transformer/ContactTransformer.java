package com.epam.project.db.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.project.db.model.Contact;

public class ContactTransformer {

	public static Contact getContact(ResultSet rs) {

		Contact contact = null;

		try {
			while (rs.next()) {
				contact = new Contact();
				contact.setId(rs.getInt(1));
				contact.setSkype(rs.getString(2));
				contact.setPhone(rs.getString(3));
				contact.setUser_id(rs.getInt(4));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contact;

	}

	public static List<Contact> getAllContact(ResultSet rs) {
		List<Contact> list = new ArrayList<Contact>();
		Contact contact = null;

		try {
			while (rs.next()) {
				contact = new Contact();
				contact.setId(rs.getInt(1));
				contact.setSkype(rs.getString(2));
				contact.setPhone(rs.getString(3));
				contact.setUser_id(rs.getInt(4));
				list.add(contact);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
}
