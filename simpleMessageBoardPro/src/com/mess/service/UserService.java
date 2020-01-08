package com.mess.service;

import com.mess.domain.User;

public interface UserService {

	
	public void regist(User user);
	
	public User login(String username,String password) ;
	
	public boolean isExistUser(String username);

	public void updateUser(User user);

	public User getUser(int id);

}
