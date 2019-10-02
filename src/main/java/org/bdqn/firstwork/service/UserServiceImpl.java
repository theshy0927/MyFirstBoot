package org.bdqn.firstwork.service;


import javax.annotation.Resource;

import org.bdqn.firstwork.mapper.UserMapper;
import org.bdqn.firstwork.model.User;
import org.bdqn.firstwork.utils.ControllerError;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
	@Resource(name = "userMapper")
	private UserMapper userMapper;
	
	@Override
	public void addUser(User user) {
		userMapper.addUser(user);
	}

	@Override
	public User getUserByToken(String token) {
		//Proxy.newProxyInstance(UserServiceImpl.class.getClassLoader(),userMapper.getClass().getInterfaces() , new );
		return userMapper.getUserByToken(token);
	}

}
