package com.epam.project.db.model.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.model.annotation.Column;
import com.epam.project.db.model.annotation.Email;
import com.epam.project.db.model.annotation.Format;
import com.epam.project.db.model.annotation.Presence;
import com.epam.project.db.model.annotation.Size;
import com.epam.project.db.model.annotation.Table;
import com.epam.project.db.model.annotation.Unique;

public class Validator {

	private Map<String, List<String>> errors;
	private boolean isValid;

	public Map<String, List<String>> getErrors() {
		return errors;
	};

	public boolean isValid() {
		isValid = false;
		validate();
		if (errors.isEmpty()) {
			isValid = true;
		}
		return isValid;
	}

	public Map<String, List<String>> validate() {
		errors = new HashMap<String, List<String>>();
		List<Field> fields = new ArrayList<>(Arrays.asList(this.getClass()
				.getDeclaredFields()));
		for (Field field : fields) {
			for (Annotation annotation : field.getDeclaredAnnotations()) {
				if (field.getAnnotation(Column.class) == null) {
					continue;
				} else if (annotation instanceof Presence) {
					presence(field, annotation);
				} else if (annotation instanceof Size) {
					checkSize(field, annotation);
				} else if (annotation instanceof Email) {
					checkEmail(field, annotation);
				} else if (annotation instanceof Format) {
					checkFormat(field, annotation);
				} else if (annotation instanceof Unique) {
					checkUnique(field, annotation);
				}

			}

		}

		return errors;
	}

	private void checkUnique(Field field, Annotation annotation) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(String.format(
					"SELECT * FROM %s",
					this.getClass().getAnnotation(Table.class).name()));
			ResultSet set = ps.executeQuery();
			if (set.next()) {
				setError(field.getAnnotation(Column.class).value(),
						((Unique) annotation).message());
			}

			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void checkEmail(Field field, Annotation annotation) {
		try {
			InternetAddress emailAddr;

			if (field.get(this) == null
					|| field.get(this).toString().equals("")) {
				throw new AddressException();
			}
			emailAddr = new InternetAddress(field.get(this).toString());

			emailAddr.validate();
		} catch (IllegalArgumentException | IllegalAccessException
				| AddressException ex) {
			setError(field.getAnnotation(Column.class).value(),
					((Email) annotation).message());

		}
	}

	private void checkFormat(Field field, Annotation annotation) {
		try {
			field.setAccessible(true);
			if (field.get(this) == null
					|| field.get(this).toString().equals("")) {
				setError(field.getAnnotation(Column.class).value(),
						((Format) annotation).message());
			}

			Pattern pattern = Pattern.compile(((Format) annotation).format());
			Matcher matcher = pattern.matcher(field.get(this).toString());
			if (!matcher.find()) {
				setError(field.getAnnotation(Column.class).value(),
						((Format) annotation).message());
			}

		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setError(String key, String message) {
		List<String> fieldErrors = errors.get(key);
		if (fieldErrors == null) {
			fieldErrors = new ArrayList<String>();
			fieldErrors.add(message);
			errors.put(key, fieldErrors);
		} else {
			fieldErrors.add(message);
		}
	}

	private void presence(Field field, Annotation annotation) {
		try {
			field.setAccessible(true);
			if (field.get(this) == null
					|| field.get(this).toString().equals("")) {
				setError(field.getAnnotation(Column.class).value(),
						((Presence) annotation).message());

			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void checkSize(Field field, Annotation annotation) {

		Method length = null;
		try {
			length = field.getType().getMethod("length");
		} catch (NoSuchMethodException | SecurityException e) {
			// NO NEED TO CATCH EXCEPTION WE JUST WANT TO CHECK MEWATHOD
			// PRESENCE
		}
		field.setAccessible(true);
		if (length != null) {

			try {

				if (field.get(this) == null) {

					setError(field.getAnnotation(Column.class).value(),
							((Size) annotation).message());

					return;
				}

				int size = (Integer) field.getType().getMethod("length")
						.invoke(field.get(this));
				if (size < ((Size) annotation).min()
						|| size > ((Size) annotation).max()) {
					setError(field.getAnnotation(Column.class).value(),
							((Size) annotation).message());
				}
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
