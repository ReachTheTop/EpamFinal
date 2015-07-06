package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.epam.project.controler.statistic.CourseStudent;
import com.epam.project.db.connection.DBConnection;
import com.epam.project.db.model.User;
import com.epam.project.db.transformer.UserTransformer;

public class UserDAO {

	public static final String SQL_UPDATE_USER = "UPDATE user SET name=?, midle_name=?, surname=?, birthday=?,"
			+ "role_id =?, password_hash=?, curriculum_vitae=?, description=?, is_active=?, is_confirmed=?, key1=?,"
			+ " image=?, email=? WHERE id=?";

	public static final String SQL_ADD_NEW_USER = "Insert into user (name,midle_name,surname,birthday,role_id,password_hash,"
			+ "curriculum_vitae,description, key1,image,email,is_confirmed)"
			+ "value(?,?,?,?,?,?,?,?,?,?,?,?)";

	public static final String SQL_GET_ALL_USERS = "SELECT SQL_CALC_FOUND_ROWS * FROM user WHERE surname REGEXP ? OR email REGEXP ? LIMIT ?, ?;";
	public static final String SQL_GET_USER = "SELECT * FROM user WHERE id=?";
	public static final String SQL_GET_USER_EMAIL = "SELECT * FROM user WHERE email=? AND is_active= 1";
	public static final String GET_ROLE = "SELECT role FROM role WHERE id = ?;";
	public static final String GET_BY_ROLE = "SELECT  * FROM user WHERE role_id = (SELECT id FROM role WHERE role = ?);";
	public static final String DELETE = "DELETE FROM user WHERE id=?";
	
	
	

	
	public static List<User> getByRole(String role, Connection connection) {
		ResultSet rs = null;
		List<User> user = null;
		try {

			PreparedStatement st = connection.prepareStatement(GET_BY_ROLE);
			st.setString(1, role);
			rs = st.executeQuery();
			user = UserTransformer.getAllUsers(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public static User getUser(Integer id, Connection connection) {

		ResultSet rs = null;
		User user = null;
		try {

			PreparedStatement st = connection.prepareStatement(SQL_GET_USER);
			if (id == null) {
				st.setNull(1, Types.INTEGER);
			} else {
				st.setInt(1, id);
			}
			rs = st.executeQuery();
			user = UserTransformer.getUser(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;

	}

	public static User getUserWhereEmail(String email, Connection connection) {

		ResultSet rs = null;
		User user = null;
		try {

			PreparedStatement st = connection
					.prepareStatement(SQL_GET_USER_EMAIL);
			st.setString(1, email);
			rs = st.executeQuery();
			user = UserTransformer.getUser(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;

	}

	public static UserCorteg getAllUsers(Connection connection, String token,
			Integer page) {
		ResultSet rs = null;
		String patterm = String.format(".*%s.*", token);
		UserCorteg users = new UserCorteg();
		try {
			connection.setAutoCommit(false);
			PreparedStatement st = connection
					.prepareStatement(SQL_GET_ALL_USERS);

			st.setString(1, patterm);
			st.setString(2, patterm);
			st.setInt(3, page * 10);
			st.setInt(4, 10);
			rs = st.executeQuery();

			users.setUsers(UserTransformer.getAllUsers(rs));

			st = connection.prepareStatement("SELECT found_rows();");
			rs = st.executeQuery();
			rs.next();
			users.setAmount(rs.getInt(1));
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public static void addNewUser(User user, Connection connection) {

		PreparedStatement stmt;

		//Connection con = DBConnection.getConnection();

		try {
			stmt = connection.prepareStatement(SQL_ADD_NEW_USER);

			stmt.setString(1, user.getName());
			stmt.setString(2, user.getMiddle_name());
			stmt.setString(3, user.getSurname());
			if (user.getBirtday() != null) {
				stmt.setDate(4, new Date(user.getBirtday().getTime()));
			} else {
				stmt.setDate(4, null);
			}
			stmt.setInt(5, user.getRole_id());
			stmt.setString(6, user.getPassword_hash());
			stmt.setString(7, user.getCurriculum_vitae());
			stmt.setString(8, user.getDescription());
			// stmt.setBoolean(9, user.getIs_active());

			stmt.setString(9, user.getKey());
			stmt.setString(10, user.getImage());
			stmt.setString(11, user.getEmail());
			if (user.getIs_confirmed() == null) {
				stmt.setBoolean(12, false);
			} else {
				stmt.setBoolean(12, user.getIs_confirmed());
			}

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void updateUser(User user, Connection connection) {

		try {

			PreparedStatement stmt = connection
					.prepareStatement(SQL_UPDATE_USER);

			stmt.setString(1, user.getName());
			stmt.setString(2, user.getMiddle_name());
			stmt.setString(3, user.getSurname());
			if (user.getBirtday() != null) {
				stmt.setDate(4, new Date(user.getBirtday().getTime()));
			} else {
				stmt.setDate(4, null);
			}
			stmt.setInt(5, user.getRole_id());
			stmt.setString(6, user.getPassword_hash());
			stmt.setString(7, user.getCurriculum_vitae());
			stmt.setString(8, user.getDescription());
			stmt.setBoolean(9, user.getIs_active());
			stmt.setBoolean(10, user.getIs_confirmed());
			stmt.setString(11, user.getKey());
			stmt.setString(12, user.getImage());
			stmt.setString(13, user.getEmail());

			stmt.setInt(14, user.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static ResultSet getUsersByToken(Connection connection, String token) {
		ResultSet resultSet = null;
		PreparedStatement ps = null;
		try {
			ps = connection
					.prepareStatement("SELECT * FROM user WHERE role_id = 2 AND email REGEXP '.*"
							+ token + ".*' LIMIT 10;");
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}

	public static String getRole(Connection connection, Integer role_id) {
		String role = null;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(GET_ROLE);
			if (role_id == null) {
				ps.setNull(1, Types.INTEGER);
			} else {
				ps.setInt(1, role_id);
			}
			ResultSet res = ps.executeQuery();
			if (res.next()) {
				role = res.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return role;
	}

	public static void changeRole(Connection connection, Integer user_id,
			Integer role_id) {
		PreparedStatement statement = null;
		try {
			statement = connection
					.prepareStatement("UPDATE user SET role_id = ? WHERE id = ?;");
			statement.setInt(1, role_id);
			statement.setInt(2, user_id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<String> getEmailNotIn(Connection connection,
			Integer group_id, List<Integer> users) {
		List<String> emails = new ArrayList<String>();
		String users_id = "( ";
		String separator = "";
		PreparedStatement statement = null;
		ResultSet result = null;
		
		if(users.isEmpty()){
			return emails;
		}
		
		try {
			for (Integer user_id : users) {
				users_id += separator;
				users_id += user_id;
				separator = ", ";
			}
			users_id += " )";
			String query = String
					.format("SELECT email FROM user WHERE id IN "
							+ "( SELECT user_id FROM group_user WHERE group_id = ? AND user_id NOT IN %s )",
							users_id);
			statement = connection.prepareStatement(query);
			statement.setInt(1, group_id);
			result = statement.executeQuery();
			
			while(result.next()){
				emails.add(result.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emails;
	}
	public static void delUser(Integer id,  Connection connection){
		
		try {
			
			PreparedStatement st = connection.prepareStatement(DELETE);
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
