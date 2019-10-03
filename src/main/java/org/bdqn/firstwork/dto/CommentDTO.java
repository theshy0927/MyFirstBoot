package org.bdqn.firstwork.dto;

import org.bdqn.firstwork.model.User;

import lombok.Data;

@Data
public class CommentDTO {

	
	private long id;
	private String content;
	private int likeCount;
	private Integer type;
	private long gmtCreated;
	private long gmtModified;
	private User creator;
	private Integer parentId;
	
	
}
