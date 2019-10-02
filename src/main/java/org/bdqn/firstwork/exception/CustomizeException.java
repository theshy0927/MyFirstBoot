package org.bdqn.firstwork.exception;

public class CustomizeException extends RuntimeException{

	private String message;
	@Override
	public String getMessage() {
		return message;
	}
	public CustomizeException(String message){
		this.message = message;
	}
	
}
