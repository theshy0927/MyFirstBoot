package org.bdqn.firstwork.utils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bdqn.firstwork.model.User;
import org.bdqn.firstwork.service.UserService;
import org.springframework.web.servlet.HandlerInterceptor;


public class TokenIntercept implements HandlerInterceptor{
	
	
	@Resource
	private UserService userService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getSession().getAttribute("user")==null) {
		Cookie [] cookies = request.getCookies();
		boolean bool = true;
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("token")) {
					User user = userService.getUserByToken(cookie.getValue());
					request.getSession().setAttribute("user", user);
					bool = false;
					break;
				}
			}
		}
		if(bool) {
			response.sendRedirect(request.getServletContext().getContextPath()+"/unLogin");
			return false;
		}
			
		
		}
		String path = request.getRequestURI();
		path = path.substring(path.indexOf("/user/")+6);
		request.getRequestDispatcher("/"+path).forward(request, response);
		return false;
	}
}
