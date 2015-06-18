package com.epam.project.controller.fblogin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.command.Action;


public class Fblogin implements Action{

	public static final String FB_APP_ID = "1583492391912147";
	public static final String FB_APP_SECRET = "3675df614d24834893a33f7f47ddeb63";
	public static final String REDIRECT_URI = "http://localhost:8080/ITMount/FbLoginServlet?action=login";

	static String accessToken = "";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(getFBAuthUrl());
		return;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "fb";
	}
	
	

	public String getFBAuthUrl() {
		String fbLoginUrl = "";
		try {
			fbLoginUrl = "http://www.facebook.com/dialog/oauth?" + "client_id="
					+ FB_APP_ID + "&redirect_uri="
					+ URLEncoder.encode(REDIRECT_URI, "UTF-8")
					+ "&scope=email";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fbLoginUrl;
	}

}
