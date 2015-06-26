package com.epam.project.helper;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class I18nTeg extends SimpleTagSupport {

	private String id;
	private Locale loc = new Locale("en", "US");
	private ResourceBundle res = ResourceBundle.getBundle("i18n",
			loc, new UTF8Control());

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void doTag() throws JspException, IOException {
		PageContext context = (PageContext) getJspContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		HttpSession session = request.getSession();
		if (session.getAttribute("bundle") != null) {
			res = (ResourceBundle) session.getAttribute("bundle");
		}else{
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			String language = null;
			String country = null;

			for (Cookie cookie2 : cookies) {
				if (cookie2.getName().equals("localLengeuge")) {
					language = cookie2.getValue();
				} else if (cookie2.getName().equals("localCountry")) {
					country = cookie2.getValue();
					loc = new Locale(language, country);
					res = ResourceBundle.getBundle("i18n", loc,new UTF8Control());
					
					break;
				}

			}

		}
		}
		JspWriter js = getJspContext().getOut();
		js.print(new String(res.getString(getId())));
	}
	
}
