package org.bdqn.firstwork.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request1, ServletResponse response1, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request =(HttpServletRequest) request1;
		HttpServletResponse response = (HttpServletResponse) response1;
        RequestParameterWrapper requestParameterWrapper = new RequestParameterWrapper(request);
		boolean bool = false;
		Cookie [] cookies = request.getCookies();
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("req_url")) {
					bool = true;
					break;
				}
			}
		}
		if(bool) {
			Map<String, Object> extraParams = new HashMap<String, Object>();
	        extraParams.put("commited", "true");
	        requestParameterWrapper.addParameters(extraParams);
		}
		
        //利用原始的request对象创建自己扩展的request对象并添加自定义参数
		
		Cookie cookie = new Cookie("req_url", request.getRequestURI());
		cookie.setPath("/");
		cookie.setMaxAge(10);
		response.addCookie(cookie);
		chain.doFilter(requestParameterWrapper, response);
	}



}
