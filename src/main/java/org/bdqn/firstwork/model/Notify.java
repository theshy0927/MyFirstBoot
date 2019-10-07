package org.bdqn.firstwork.model;


import lombok.Data;

@Data
public class Notify {

	private long id;
	private Integer outerId;
	private long notifier;
	private long receiver;
	private Integer isQuestionNotify;
	private long gmtCreate;
	private boolean  read;
	
	
	
	public Notify(Integer outerId, long notifier, long receiver, Integer isQuestionNotify, long gmtCreate) {
		super();
		this.outerId = outerId;
		this.notifier = notifier;
		this.receiver = receiver;
		this.isQuestionNotify = isQuestionNotify;
		this.gmtCreate = gmtCreate;
	}



	public Notify() {}
	
	
}
