package org.bdqn.firstwork.mapper;

import org.apache.ibatis.annotations.Param;
import org.bdqn.firstwork.model.Comment;

public interface CommentMapper {

	int addComment(Comment comment);

	int updateCommentCount(@Param("count") Integer count,@Param("parentId") Integer parentId);

}
