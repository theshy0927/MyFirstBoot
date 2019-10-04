package org.bdqn.firstwork.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.bdqn.firstwork.dto.QuestionDTO;
import org.bdqn.firstwork.model.Question;
import org.bdqn.firstwork.model.User;
import org.bdqn.firstwork.service.QuestionService;
import org.bdqn.firstwork.utils.CommentType;
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
	public String addorUpdateQuestion(Question question,HttpServletRequest request,Model model) {
		String returnUrl = question.getId()==0? "publish" :"forward:/goPublish?id="+question.getId();
		model.addAttribute("id", question.getId());
		model.addAttribute("description", question.getDescription());
		model.addAttribute("tag", question.getTag());
		if(question.getTitle()==null || question.getTitle().trim().equals("")) {
			model.addAttribute("error", "标题不能为空");
		}else if(question.getDescription()==null  || question.getDescription().trim().equals("")) {
			model.addAttribute("title", question.getTitle());
			model.addAttribute("error", "描述不能为空");
		}else {
			//判断是更新还是新增
			if(question.getId()==0) {
			User user = (User) request.getSession().getAttribute("user");
			question.setCreater(user.getId());
			questionService.addQuestion(question);
			if(question.getId()==0) {
				model.addAttribute("error", "系统错误，请联系系统管理员");
			}else {
				returnUrl = "redirect:/";
			}
			}else {
				questionService.updateQuestion(question);
				returnUrl = "redirect:/profile/questions/"+question.getCreater();
			}
		}
		return returnUrl;
	}
	
	@RequestMapping("/goPublish")
	public String goPublish(HttpServletRequest request,@RequestParam(value = "id" , required = false , defaultValue = "0") Integer id , Model model) {
		
		if(id!=0 && request.getAttribute("error")==null) {
			QuestionDTO dto = questionService.getQuestionMeg(id,true);
			model.addAttribute("id", dto.getId());
			model.addAttribute("description", dto.getDescription());
			model.addAttribute("tag", dto.getTag());
			model.addAttribute("title", dto.getTitle());
		}
		return "publish";
	}
	
	
	@RequestMapping("/question/{id}")
	public String question(HttpServletRequest request,Model model,@PathVariable(value = "id") Integer id,
			@RequestParam(value = "commited" , required = false ,defaultValue = "false") boolean commited){
		QuestionDTO dto = questionService.getQuestionMeg(id,commited);
		model.addAttribute("question", dto);
		model.addAttribute("commentDTO",dto.getCommonCount()>0?questionService.getCommonDTO(dto.getId(),CommentType.Question):new ArrayList<>());
		model.addAttribute("sameQuestion",questionService.getSameQuestion(dto));
		
		return "question";
	}
	
	
	
	
	
}
