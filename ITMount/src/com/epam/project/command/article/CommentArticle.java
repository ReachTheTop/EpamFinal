package com.epam.project.command.article;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;
import com.epam.project.db.model.Message;
import com.epam.project.db.model.User;
import com.epam.project.db.service.MessageService;

public class CommentArticle implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Message comment = new Message();
		String content = request.getParameter("content");
		User sender = (User) request.getSession().getAttribute("user");
		Integer article_id = Integer.parseInt(request.getParameter("article_id"));
		
		comment.setContent(content);
		comment.setSender_id(sender.getId());
		MessageService.leaveArticleComment(article_id, comment);
		
		response.sendRedirect(request.getHeader("referer"));
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "comment";
	}

}
