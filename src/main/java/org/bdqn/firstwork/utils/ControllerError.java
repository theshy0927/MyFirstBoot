package org.bdqn.firstwork.utils;

public enum ControllerError implements IControllerError {
	
	not_find("该帖子不存在或已经被删除！！！"),
	;

	@Override
	public String getMessage() {
		return message;
	}

	private String message;
	
	ControllerError(String message){
		this.message=message;
	}
	
	
	

}
