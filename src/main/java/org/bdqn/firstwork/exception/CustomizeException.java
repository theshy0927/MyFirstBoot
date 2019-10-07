package org.bdqn.firstwork.exception;

import org.bdqn.firstwork.enums.ControllerError;

public class CustomizeException extends RuntimeException{

	private String message;
private Integer code;
	
	public Integer getCode() {
		return code;
	}
	@Override
	public String getMessage() {
		return message;
	}
	public CustomizeException(ControllerError error){
		this.code=error.getCode();
		this.message = error.getMessage();
	}
	
}
