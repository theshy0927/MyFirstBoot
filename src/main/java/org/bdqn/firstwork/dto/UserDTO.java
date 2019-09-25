package org.bdqn.firstwork.dto;

import java.io.Serializable;
import java.util.List;

import org.bdqn.firstwork.model.Question;

import lombok.Data;

@Data
public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id;
	private String token;
	private Long gtmCreated=System.currentTimeMillis();
	private Long gtmModified=System.currentTimeMillis();
	private String name;
	private String avatarUrl;
	private List<Question> questions;
	
}
