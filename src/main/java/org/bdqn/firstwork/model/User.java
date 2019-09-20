package org.bdqn.firstwork.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private String id;
	private String token;
	private Long gtmCreated=System.currentTimeMillis();
	private Long gtmModified=System.currentTimeMillis();
	private String name;

}
