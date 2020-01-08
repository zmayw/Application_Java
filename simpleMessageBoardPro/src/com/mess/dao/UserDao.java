package com.mess.dao;

import com.mess.domain.User;

public interface UserDao {

	
	public User login(String username,String password);
	
	public boolean isExistUser(String username);
	
	public void register(User user);

	public void updateUser(User user);

	public User getUser(int id);
}
