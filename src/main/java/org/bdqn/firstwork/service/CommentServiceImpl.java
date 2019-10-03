package org.bdqn.firstwork.service;

import org.bdqn.firstwork.exception.CustomizeException;
import org.bdqn.firstwork.mapper.CommentMapper;
import org.bdqn.firstwork.model.Comment;
import org.bdqn.firstwork.utils.CommentType;
import org.bdqn.firstwork.utils.ControllerError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("CommentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	@Transactional
	public int addComment(Comment comment) {
		System.out.println(comment);
		if(comment.getParentId()==null||comment.getParentId()==0) {
			throw new CustomizeException(ControllerError.comment_not_find);
		}
		if(comment.getType()==0||!CommentType.isExists( comment.getType())) {
			throw new CustomizeException(ControllerError.type_param_error);
		}
		int result = 0;
		try {
			result = commentMapper.addComment(comment);
			if(comment.getType()==CommentType.Question.getType()) {
				commentMapper.updateCommentCount(1,comment.getParentId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomizeException(ControllerError.question_not_find);
		}
		
		return result;
	}

}
