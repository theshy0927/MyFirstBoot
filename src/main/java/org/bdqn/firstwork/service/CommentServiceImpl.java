package org.bdqn.firstwork.service;

import org.bdqn.firstwork.enums.CommentType;
import org.bdqn.firstwork.enums.ControllerError;
import org.bdqn.firstwork.exception.CustomizeException;
import org.bdqn.firstwork.mapper.CommentMapper;
import org.bdqn.firstwork.model.Comment;
import org.bdqn.firstwork.model.Notify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("CommentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	@Transactional
	public void addComment(Comment comment,long notifier) {
		if(comment.getParentId()==null||comment.getParentId()==0) {
			throw new CustomizeException(ControllerError.comment_not_find);
		}
		if(comment.getType()==0||!CommentType.isExists( comment.getType())) {
			throw new CustomizeException(ControllerError.type_param_error);
		}
		try {
			 commentMapper.addComment(comment);
			addNotified(comment,notifier);
			if(comment.getType()==CommentType.Question.getType()) {
				commentMapper.updateQuestionCommentCount(1,comment.getParentId());
			}else {
				//评论回复数更新
				commentMapper.updateCommonetCommonCount(1,comment.getParentId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomizeException(ControllerError.question_not_find);
		}
		
	}
	
	public void addNotified(Comment comment,Long nodifier) {
		Notify notify = new Notify(comment.getParentId(),nodifier,comment.getCreatorId(),comment.getType(),System.currentTimeMillis());
		
		commentMapper.addNotified(notify);
	}
	

}
