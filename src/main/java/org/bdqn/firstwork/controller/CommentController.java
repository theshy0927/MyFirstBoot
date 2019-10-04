package org.bdqn.firstwork.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bdqn.firstwork.dto.CommentDTO;
import org.bdqn.firstwork.dto.ResultDTO;
import org.bdqn.firstwork.model.Comment;
import org.bdqn.firstwork.model.User;
import org.bdqn.firstwork.service.CommentService;
import org.bdqn.firstwork.service.QuestionService;
import org.bdqn.firstwork.utils.CommentType;
import org.bdqn.firstwork.utils.ControllerError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	@Autowired
	private QuestionService questionService;
	@RequestMapping("/addComment")
	@ResponseBody
	public ResultDTO addComment(@RequestBody Comment comment,HttpServletRequest request) {
		User attribute =  (User)(request.getSession().getAttribute("user"));
		if(attribute==null) {
			return ResultDTO.errorOf(ControllerError.no_login);
		}
		comment.setCreatorId(attribute.getId());
		comment.setGmtCreated(System.currentTimeMillis());
		comment.setGmtModified(System.currentTimeMillis());
		comment.setLikeCount(1);
		commentService.addComment(comment);
		return ResultDTO.okOf(comment);
	}
	
	@RequestMapping("comment/{id}")
	@ResponseBody
	public ResultDTO get(@PathVariable("id") Integer parentId) {
		List<CommentDTO> commonDTO = questionService.getCommonDTO(parentId, CommentType.Comment);
		return ResultDTO.okOf(commonDTO);
	}
	
}
