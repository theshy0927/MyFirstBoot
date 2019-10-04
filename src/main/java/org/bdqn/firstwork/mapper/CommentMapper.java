package org.bdqn.firstwork.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.bdqn.firstwork.model.Comment;

public interface CommentMapper {

	int addComment(Comment comment);

	int updateQuestionCommentCount(@Param("count") Integer count,@Param("parentId") Integer parentId);

	List<Comment> getCommentList(@Param("questionId")Integer questionId, @Param("type")Integer type);

	int updateCommonetCommonCount(@Param("count")int i,@Param("parentId") Integer parentId);

}
