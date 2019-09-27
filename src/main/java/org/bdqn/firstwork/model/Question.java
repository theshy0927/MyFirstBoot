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
	private long gmtCreate = System.currentTimeMillis();
	private long gmtModified = System.currentTimeMillis();
	private Integer viewCount = 0;
	private Integer commonCount = 0;
	private Integer likeCount = 0;
	private String tag;
	private long creater;
	
}
