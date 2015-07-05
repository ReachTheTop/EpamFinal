package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.epam.project.controler.statistic.CourseComplited;
import com.epam.project.controler.statistic.CourseStudent;
import com.epam.project.db.model.Course;
import com.epam.project.db.transformer.CourseTransformer;
import com.epam.project.db.transformer.UserTransformer;

public class CourseDAO {

	private static final String BASE_GROUP = "INSERT INTO group1(course_id, name, is_active, confirmed) VALUE (?,?,?,?);";
	private static final String CALC_ROWS = "SELECT found_rows();";
	private static final String GET_ALL = "SELECT SQL_CALC_FOUND_ROWS * FROM course WHERE name REGEXP ? LIMIT ?,?;";

	private static final String SELECTALL = "SELECT * FROM course WHERE is_active = 1;";

	private static final String INSERT = "INSERT INTO course(name,icon,description) VALUES(?,?,?);";
	private static final String UPDATE = "UPDATE course SET name=?, icon=?, description=? WHERE id=?";
	private static final String DELETE = "DELETE FROM course WHERE id=?";

	private static final String SELECT = "SELECT * FROM course WHERE id=?";

	private static final String TRIGER = "UPDATE course set is_active = !is_active  WHERE id = ?";
	
	public static final String GET_COUNT_STUDENT_COURSES = "select c.name,count(*) as kk from group_user gu join group1 g"
			+ " join course c where c.id=g.course_id and gu.group_id=g.id group by c.name";
	public static final String GET_COUNT_STUDENT_COURSES_COMPLITED_YEAR = "select count(*) as counStudent, "
			+ "year(g.date_exam) as year from group1 g join group_user  gu  where g.id = gu.group_id and g.is_active ='0'"
			+ " and g.course_id=? group by year(g.date_exam)";
	
	public static List<CourseComplited> getCountStudentCoursesComplited(
			Connection connection, Integer id) {
		
		ResultSet rs = null;
		List<CourseComplited> countUser = null;
		try {

			PreparedStatement st = connection.prepareStatement(GET_COUNT_STUDENT_COURSES_COMPLITED_YEAR);
			st.setInt(1, id);
			
			rs = st.executeQuery();
			countUser = CourseTransformer.getCountCoursesStudentComplited(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countUser;
		
		
	}
	
	
	public static List<CourseStudent> getStudentCountCourses(Connection connection){
		
		ResultSet rs = null;
		List<CourseStudent> countUser = null;
		try {

			PreparedStatement st = connection.prepareStatement(GET_COUNT_STUDENT_COURSES);
			
			rs = st.executeQuery();
			countUser = CourseTransformer.getCountCoursesStudent(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countUser;
	}
	
	
	public static void trigerCourse(Course course, Connection connection) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(TRIGER);

			st.setInt(1, course.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static Integer addCourse(Course course, Connection connection) {
		PreparedStatement st = null;
		Integer course_id = null;
		try {
			connection.setAutoCommit(false);
			st = connection.prepareStatement(INSERT,
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, course.getName());
			st.setString(2, course.getIcon());
			st.setString(3, course.getDescription());

			st.executeUpdate();

			ResultSet resultSet = st.getGeneratedKeys();
			resultSet.next();
			course_id = resultSet.getInt(1);
			st = connection.prepareStatement(BASE_GROUP);

			st.setInt(1, course_id);
			st.setString(2, "Base");
			st.setBoolean(3, true);
			st.setBoolean(4, false);

			st.executeUpdate();

			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return course_id;

	}

	public static void delCourse(Integer id, Connection connection) {

		try {

			PreparedStatement st = connection.prepareStatement(DELETE);
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void updateCourse(Course course, Connection connection) {
		try {

			PreparedStatement st = connection.prepareStatement(UPDATE);

			st.setString(1, course.getName());
			st.setString(2, course.getIcon());
			st.setString(3, course.getDescription());

			st.setInt(4, course.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Course> getAllActiveCourses(Connection connection) {
		ResultSet rs = null;
		List<Course> list = null;
		try {

			PreparedStatement st = connection.prepareStatement(SELECTALL);
			rs = st.executeQuery();
			list = CourseTransformer.getAllCourse(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public static Course getCourse(Integer id, Connection connection) {
		ResultSet rs = null;
		Course course = null;
		try {

			PreparedStatement st = connection.prepareStatement(SELECT);
			st.setInt(1, id);
			rs = st.executeQuery();
			course = CourseTransformer.getCourse(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course;

	}

	public static CourseCorteg getAllCurses(Connection connection,
			String searchToken, Integer page) {
		CourseCorteg courses = new CourseCorteg();
		ResultSet countResult = null;
		PreparedStatement ps = null;
		String pattern = String.format(".*%s.*", searchToken);
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(GET_ALL);
			ps.setString(1, pattern);
			ps.setInt(2, page * 10);
			ps.setInt(3, 10);
			courses.setResultSet(ps.executeQuery());

			ps = connection.prepareStatement(CALC_ROWS);
			countResult = ps.executeQuery();
			countResult.next();
			courses.setAmount(countResult.getInt(1));

			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return courses;

	}


	

	

}
