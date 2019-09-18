package org.bdqn.firstwork.model;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String token;
	private Long gtmCreated=System.currentTimeMillis();
	private Long gtmModified=System.currentTimeMillis();
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Long getGtmCreated() {
		return gtmCreated;
	}
	public void setGtmCreated(Long gtmCreated) {
		this.gtmCreated = gtmCreated;
	}
	public Long getGtmModified() {
		return gtmModified;
	}
	public void setGtmModified(Long gtmModified) {
		this.gtmModified = gtmModified;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
