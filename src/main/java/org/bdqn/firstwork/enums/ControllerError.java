package org.bdqn.firstwork.enums;

public enum ControllerError implements IControllerError {
	
	not_find(2001,"该帖子不存在或已经被删除！！！"),
	no_login(2002,"请登录后在重试"),
	comment_not_find(2003,"评论找不到，可能该评论已经被删除"),
	question_not_find(2004,"问题找不到，可能该问题已经被删除"),
	type_param_error(2005,"问题类型有问题或不存在"),
	
	
	
	server_error(3001,"服务器冒烟了请稍后再重试"),
	;
	@Override
	public String getMessage() {
		return message;
	}

	private String message;
	private Integer code;
	
	public Integer getCode() {
		return code;
	}

	ControllerError(Integer code,String message){
		this.code=code;
		this.message=message;
	}
	
	
	

}
