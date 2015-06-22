package com.epam.project.filter;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.epam.project.db.model.User;

/**
 * Servlet Filter implementation class EndSessionFilter
 */
@WebFilter("/EndSessionFilter")
public class EndSessionFilter implements Filter {

	
    /**
     * Default constructor. 
     */
    public EndSessionFilter() {
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
		HttpSession session = ((HttpServletRequest)request).getSession(false);
		
		if (session != null) {
			User user = (User) session.getAttribute("user");
			if(user==null){
			if(((HttpServletRequest)request).getRequestURI().equals("/ITMount/CourseServlet")){
				String action = ((HttpServletRequest)request).getParameter("action");
				
				if(action!=null&&(action.equals("register")||action.equals("create")||action.equals("delete")
						||action.equals("edit")||action.equals("triger")||action.equals("update"))){
					((HttpServletResponse)response).sendRedirect("/ITMount/login");
				     return;
				}
			}else{
				((HttpServletResponse)response).sendRedirect("/ITMount/login");
			     return;
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
