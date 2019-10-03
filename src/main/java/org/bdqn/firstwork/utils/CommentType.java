package org.bdqn.firstwork.utils;

public enum CommentType {
	
	Comment(1),//回复的是评论
	Question(2);//回复的是问题
	
	private Integer type;
	public Integer getType() {
		return type;
	}
	CommentType(Integer type){
		this.type=type;
	}
	
	public static boolean isExists(Integer type) {
		for (CommentType i : values()) {
			if(i.type==type) {
				return true;
			}
		}
		return false;
	}
	
	
}
