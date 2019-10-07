package org.bdqn.firstwork.dto;

import java.io.Serializable;

import org.bdqn.firstwork.model.User;

import lombok.Data;

@Data
public class QuestionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private String description;
	private long gmtCreate = System.currentTimeMillis();
	private long gmtModified = System.currentTimeMillis();
	private Integer viewCount = 0;
	private Integer commonCount = 0;
	private Integer likeCount = 0;
	private String tag;
	private long creater;
	private User user;
}
