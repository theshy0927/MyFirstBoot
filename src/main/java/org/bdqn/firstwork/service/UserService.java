package org.bdqn.firstwork.service;

import org.bdqn.firstwork.model.User;

public interface UserService {
	
	public  void addUser(User user);
	
	public User getUserByToken(String Token);
	
}
