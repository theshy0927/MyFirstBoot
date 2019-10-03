package org.bdqn.firstwork.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bdqn.firstwork.dto.ResultDTO;
import org.bdqn.firstwork.exception.CustomizeException;
import org.bdqn.firstwork.utils.ControllerError;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ModelAndView error(Throwable ex,Model model,HttpServletRequest request,HttpServletResponse response) {
		String contentType = request.getContentType();
		if("application/json".equals(contentType)) {
			ResultDTO dto =null;
			if(ex instanceof CustomizeException) {
				dto = ResultDTO.errorOf((CustomizeException)ex);
			}else {
				dto = ResultDTO.errorOf(ControllerError.server_error);
			}
			PrintWriter writer = null;
			try {
				response.setContentType("application/json");
				response.setCharacterEncoding("utf-8");
				writer	= response.getWriter();
				writer.print(JSON.toJSONString(dto));
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				writer.close();
			}
			return null;
		}else {
		if(ex instanceof CustomizeException) {
			model.addAttribute("message", ex.getMessage());
		}else {
			model.addAttribute("message", "服务器冒烟了，请你稍后再重试");
		}
		}
	return 	new ModelAndView("error");
	}
	
}
