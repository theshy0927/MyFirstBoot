package org.bdqn.firstwork.enums;

public enum NotifyType {
	common_notify(1,"评论了你的问题"),
	question_notify(2,"回复了你的问题");
	
	private Integer type;
	private String desc;
	private NotifyType(Integer type, String desc) {
		this.type = type;
		this.desc = desc;
	}
	public Integer getType() {
		return type;
	}
	public String getDesc() {
		return desc;
	}
	
	public static String nameOf(Integer i) {
		for (NotifyType type : values()) {
			if(type.type==i) {
				return type.desc;
			}
		}
		return "";
	}
	
}
