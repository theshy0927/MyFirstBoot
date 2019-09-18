package org.bdqn.firstwork.controller;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bdqn.firstwork.dto.AccessTokenDTO;
import org.bdqn.firstwork.dto.GithubUser;
import org.bdqn.firstwork.model.User;
import org.bdqn.firstwork.service.UserService;
import org.bdqn.firstwork.utils.GitHubProvider;
import org.bdqn.firstwork.utils.RedisUtils;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
public class SpringControl {
	
	@Resource
	private GitHubProvider gitHubProvider; 
	
	@Resource(name = "redisUtils")
	private RedisUtils redis;
	
	@Resource
	private UserService userService;
	
	
	/**
	    * 默认主页为index.html 如果客户端存在token 则直接登录 ，否则需要手动登录
	 * @param request
	 * @return
	 */
	@RequestMapping("/")
	public String Welcome(HttpServletRequest request) {
		Cookie [] cookies = request.getCookies();
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("token")) {
					User user = userService.getUserByToken(cookie.getValue());
					request.getSession().setAttribute("user", user);
					break;
				}
			}
		}
		//request.setAttribute("b", "我太难了");
		return "index";
		//return "redirect:/index.html";	
	}
	
	
	/**
	   *     处理调用github三方登录的返回数据  完成github用户登录
	 * @param response
	 * @param code 
	 * @param state
	 * @return
	 */
	@RequestMapping("/callback")
	public String authLoginCallBack(HttpServletResponse response,@RequestParam("code") String code,@RequestParam("state") String state) {
		String url = "redirect:/";
		
		AccessTokenDTO dto = new AccessTokenDTO();
		dto.setClient_id("9393446dc7d775d12b5c");
		dto.setCode(code);
		dto.setState(state);
		dto.setRedirect_uri("http://localhost:8887/callback");
		dto.setClient_secret("46b4110c09c0d80365c476096906e8189089a73e");
	GithubUser gitUser =	gitHubProvider.createUserByAccessToken(gitHubProvider.getAccessToken(dto));
		if(gitUser!=null&& !redis.hasKey("uid"+gitUser.getId())) {
			User user = new User();
			user.setName(gitUser.getLogin());
			user.setToken(UUID.randomUUID().toString());
			userService.addUser(user);
			redis.set("uid"+gitUser.getId(), JSON.toJSONString(user));
			response.addCookie(new Cookie("token",user.getToken()));
		}else if(gitUser!=null&&redis.hasKey("uid"+gitUser.getId())) {
			User user = JSON.toJavaObject(JSON.parseObject(redis.get("uid"+gitUser.getId()).toString()), User.class);
			response.addCookie(new Cookie("token",user.getToken()));
		}
	return url;
	}
}