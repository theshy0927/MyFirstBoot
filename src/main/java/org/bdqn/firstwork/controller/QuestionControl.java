package org.bdqn.firstwork.controller;

import javax.servlet.http.HttpServletRequest;

import org.bdqn.firstwork.dto.QuestionDTO;
import org.bdqn.firstwork.model.Question;
import org.bdqn.firstwork.model.User;
import org.bdqn.firstwork.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionControl {
	
	@Autowired
	private QuestionService questionService;
	
	@PostMapping("addQuestion")
	public String addQuestion(Question question,HttpServletRequest request,Model model) {
		String returnUrl = "publish";
		model.addAttribute("description", question.getDescription());
		if(question.getTitle()==null || question.getTitle().trim().equals("")) {
			model.addAttribute("error", "标题不能为空");
		}else if(question.getDescription()==null  || question.getDescription().trim().equals("")) {
			model.addAttribute("title", question.getTitle());
			model.addAttribute("error", "描述不能为空");
		}else {
			User user = (User) request.getSession().getAttribute("user");
			question.setCreater(user.getId());
			questionService.addQuestion(question);
			if(question.getId()==0) {
				model.addAttribute("error", "系统错误，请联系系统管理员");
			}else {
				returnUrl = "redirect:/";
			}
		}
		return returnUrl;
	}
	
	@RequestMapping("/goPublish")
	public String goPublish() {
		return "publish";
	}
	
	
	@RequestMapping("/question/{id}")
	public String question(HttpServletRequest request,Model model,@PathVariable(value = "id") Integer id,@RequestParam(value = "commited" , required = false ,defaultValue = "false") boolean commited){
		QuestionDTO dto = questionService.getQuestionMeg(id,commited);
		model.addAttribute("question", dto);
		return "question";
	}
	
}
