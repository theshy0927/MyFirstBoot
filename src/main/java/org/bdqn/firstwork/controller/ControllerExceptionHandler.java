package org.bdqn.firstwork.controller;

import org.bdqn.firstwork.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ModelAndView error(Throwable ex,Model model) {
		System.out.println(1);
		if(ex instanceof CustomizeException) {
			model.addAttribute("message", ex.getMessage());
		}else {
			
			model.addAttribute("message", "服务器冒烟了，请你稍后再重试");
		}
	return 	new ModelAndView("error");
	}
	
}
