package org.bdqn.firstwork.util;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bdqn.firstwork.model.Question;
import org.bdqn.firstwork.model.User;
import org.bdqn.firstwork.service.QuestionService;
import org.bdqn.firstwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;


public class TokenIntercept implements HandlerInterceptor{
	
	
	@Resource
	private UserService userService;
	@Autowired
	private QuestionService questionService;
	
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
					request.getSession().setAttribute("unReadCount", questionService.getUnReadCount(user.getId()));
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
