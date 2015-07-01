package com.epam.project.controller.task;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.epam.project.command.Action;
import com.epam.project.db.model.Task;
import com.epam.project.db.service.TaskService;
import com.epam.project.util.file.DeleteFile;
import com.epam.project.util.file.UploadFile;

public class UpdateTaskCommand implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		boolean correctDate = true;

		Task task = TaskService.getTask(Integer.parseInt(request
				.getParameter("task_id")));
		task.setDescription(request.getParameter("task_description"));
		task.setName(request.getParameter("task_name"));

		String stringDateDeadline = request.getParameter("task_deadline");

		Date dateDeadline = null;
		DateFormat deadline = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		try {
			dateDeadline = deadline.parse(stringDateDeadline);
		} catch (ParseException e) {

			e.printStackTrace();

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}

		task.setAvailable(true);
		task.setIs_active(true);

		int idGroup = Integer.parseInt(request.getParameter("group_id"));
		task.setGroupID(idGroup);
		task.setDeadline(dateDeadline);

		Part file = request.getPart("fileUpdate");
		System.out.println(file.getSubmittedFileName().isEmpty());

		UploadFile m = new UploadFile();

		if (task.isValid()) {

			if (task.getDeadline() != null) {
				if (checkDate(task.getDeadline()) == false) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					return;
				}

				if (file.getSubmittedFileName().isEmpty()) {

				
					TaskService.updateTask(task);
					return;
				} else {

					if (file.getSize() == 0) {

						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						return;
					}

					String fileName = m.uploadFile(file,
							request.getServletContext(), "group_id_" + idGroup);
					if (task.getFile() != null) {
						DeleteFile.deleteFile(task.getFile(),
								request.getServletContext());
					}
					task.setFile(fileName);
					if(task.isValid()){
					TaskService.updateTask(task);
					}else{
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						return;	
					}
				}

			}
			return;

		} else {

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}

	}

	@Override
	public String getName() {

		return "updateTask";
	}

	private boolean checkDate(Date deadline) {

		boolean dateCorrect = true;

		Calendar cal = Calendar.getInstance();
		cal.setTime(deadline);

		int year = cal.get(Calendar.YEAR);

		if (year < 1970 || year > 2030) {
			dateCorrect = false;
		}

		return dateCorrect;
	}

}
