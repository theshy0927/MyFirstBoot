package org.bdqn.firstwork.model;

import lombok.Data;

@Data
public class Comment {

	private long id;
	private String content;
	private int likeCount;
	private Integer type;
	private long gmtCreated;
	private long gmtModified;
	private Integer parentId;
	private long creatorId;
	private Integer commonCount;
}
