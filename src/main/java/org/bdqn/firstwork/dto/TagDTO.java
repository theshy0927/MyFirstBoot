package org.bdqn.firstwork.dto;

import java.util.List;

import lombok.Data;

@Data
public class TagDTO {

	
	public String categoryName;//显示的标签父级别名称
	public String tagId;//父级标签在前端的id值
	public List<String> tags;
}
