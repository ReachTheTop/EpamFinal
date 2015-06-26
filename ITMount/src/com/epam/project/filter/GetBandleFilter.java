package com.epam.project.filter;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class GetBandleFilter
 */
@WebFilter("/GetBandleFilter")
public class GetBandleFilter implements Filter {

    /**
     * Default constructor. 
     */
    public GetBandleFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		Cookie[] cookies = ((HttpServletRequest) request).getCookies();
		if (cookies != null) {
			String language = null;
			String country = null;

			for (Cookie cookie2 : cookies) {
				if (cookie2.getName().equals("localLengeuge")) {
					language = cookie2.getValue();
				} else if (cookie2.getName().equals("localCountry")) {
					country = cookie2.getValue();
				 Locale	loc = new Locale(language, country);
				
					ResourceBundle res = ResourceBundle.getBundle("i18n", loc);
					session.setAttribute("bundle", res);
					break;
				}

			}
			}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
