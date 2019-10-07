package org.bdqn.firstwork.controller;

import javax.servlet.http.HttpServletRequest;

import org.bdqn.firstwork.dto.NotifyDTO;
import org.bdqn.firstwork.dto.PaginationDTO;
import org.bdqn.firstwork.dto.QuestionDTO;
import org.bdqn.firstwork.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profile/")
public class ProfileControl {
	
	@Autowired
	private QuestionService questionService;
	
	@RequestMapping("/questions/{id}")
	public String questions(
			HttpServletRequest request,
			@PathVariable("id") Integer id,Model model,
			@RequestParam(defaultValue = "1",required = false) Integer curPage,
			@RequestParam(defaultValue = "8",required = false) Integer size) {
		PaginationDTO<QuestionDTO> questionList = questionService.questionList(curPage, size, id);
		questionList.setUrl("/user/profile/questions/"+id);
		questionList.setType("questions");
		model.addAttribute("questionList", questionList);
		return "/profile";
	}
	
	@RequestMapping("/replies/{id}")
	public String replies(
			HttpServletRequest request,
			@PathVariable("id") Integer id,Model model,
			@RequestParam(defaultValue = "1",required = false) Integer curPage,
			@RequestParam(defaultValue = "8",required = false) Integer size) {
		PaginationDTO<NotifyDTO> repliesList = questionService.repliesList(curPage, size, id);
		repliesList.setUrl("/user/profile/replies/"+id);
		repliesList.setType("replies");
		model.addAttribute("questionList", repliesList);
		
		return "/profile";
	}
	
}
