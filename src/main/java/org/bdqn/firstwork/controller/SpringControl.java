package org.bdqn.firstwork.controller;


import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bdqn.firstwork.dto.AccessTokenDTO;
import org.bdqn.firstwork.dto.GithubUser;
import org.bdqn.firstwork.dto.PaginationDTO;
import org.bdqn.firstwork.dto.QuestionDTO;
import org.bdqn.firstwork.model.User;
import org.bdqn.firstwork.service.QuestionService;
import org.bdqn.firstwork.service.UserService;
import org.bdqn.firstwork.utils.GitHubProvider;
import org.bdqn.firstwork.utils.RedisUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;

@Controller
public class SpringControl {
	
	@Resource
	private GitHubProvider gitHubProvider; 
	
	@Resource(name = "redisUtils")
	private RedisUtils redis;
	
	@Resource
	private UserService userService;
	
	@Resource
	private QuestionService questionService;
	
	
	/**
	    * 默认主页为index.html 如果客户端存在token 则直接登录 ，否则需要手动登录
	 * @param request
	 * @return
	 */
	@RequestMapping("/")
	public String Welcome(HttpServletRequest request,
			@RequestParam(defaultValue = "1",required = false) Integer curPage,
			@RequestParam(defaultValue = "2",required = false) Integer size) {
		
		PaginationDTO<QuestionDTO> questionList = questionService.questionList(curPage ,size,null);
		questionList.setUrl("/");
		request.setAttribute("questionList", questionList);
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
		String url = "redirect:/user/";
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
			user.setAvatarUrl(gitUser.getAvatarUrl());
			userService.addUser(user);
			redis.set("uid"+gitUser.getId(), JSON.toJSONString(user),60*60*24*7);
			response.addCookie(new Cookie("token",user.getToken()));
		}else if(gitUser!=null&&redis.hasKey("uid"+gitUser.getId())) {
			User user = JSON.toJavaObject(JSON.parseObject(redis.get("uid"+gitUser.getId()).toString()), User.class);
			Cookie cookie = new Cookie("token",user.getToken());
			cookie.setMaxAge(60*60*24);
			response.addCookie(cookie);
		}
	return url;
	}
	
	//集中处理过滤未登录
	@GetMapping(value = "/unLogin")
	public String unLogin(Model model) {
		model.addAttribute("error","请登录后在进行该操作");
		
		
		return "forward:/";
	}
	
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) throws InterruptedException {
		request.getSession().removeAttribute("user");
		Cookie [] cookies = request.getCookies();
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("token")) {
					Cookie cookie2 = new Cookie("token", "");
					cookie2.setMaxAge(0);
					response.addCookie(cookie2);
					break;
				}
			}
		}
		request.setAttribute("callBack", "退出成功");
		return "forward:/";
	}
}
