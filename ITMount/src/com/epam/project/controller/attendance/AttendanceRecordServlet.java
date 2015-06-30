package com.epam.project.controller.attendance;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.epam.project.db.dao.JournalDAO;

/**
 * Servlet implementation class AttendanceRecordServlet
 */
@WebServlet("/att-record")
public class AttendanceRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AttendanceRecordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			JSONObject jObj = new JSONObject(request.getParameter("data"));
			Iterator it = jObj.keys();
			while (it.hasNext()) {
				String key = (String) it.next(); // get key
				Object o = jObj.get(key); // get value
				System.out.println("Key: " + key + " " + "Object" + (String) o);
				//JournalDAO.updateVisit(visit, id);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
