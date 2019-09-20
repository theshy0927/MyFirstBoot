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
	private User user;
}
