package org.bdqn.firstwork.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest  = (HttpServletRequest) request;
		if (httpRequest.getSession().getAttribute("user")==null) {
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		servletResponse.sendRedirect(request.getServletContext().getContextPath()+"/unLogin");
		return;
		}else {
			String path = httpRequest.getRequestURI();
			path = path.substring(path.indexOf("/user/")+6);
			request.getRequestDispatcher("/"+path).forward(httpRequest, response);
			return;
		}
	}



}
