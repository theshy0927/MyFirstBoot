package org.bdqn.firstwork.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Question implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//select id,title,description,gmt_create,gmt_modified,view_count,common_count,like_count,tag,creater from question
	private int id;
	private String title;
	private String description;
	private long gmtCreate;
	private long gmtModified;
	private Integer viewCount;
	private Integer commonCount;
	private Integer likeCount;
	private String tag;
	private long creater;
	
}
