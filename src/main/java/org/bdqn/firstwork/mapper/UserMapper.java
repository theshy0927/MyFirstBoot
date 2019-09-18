package org.bdqn.firstwork.mapper;

import org.bdqn.firstwork.model.User;

public interface UserMapper {
	//a  添加用户
	public void addUser(User user);
	
	//b  通过token 获取对应的用户信息
	public User getUserByToken(String Token);
	
}
