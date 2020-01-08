package com.mess.service.Impl;

import com.mess.dao.UserDao;
import com.mess.dao.impl.UserDaoImpl;
import com.mess.domain.User;
import com.mess.service.UserService;


public class UserServiceImpl implements UserService {


	

	public User login(String username,String password) {
			UserDao userDao=new UserDaoImpl();
			User user=new User();
			user=userDao.login(username,password);
			return user;
	}
	
	/*
	 * ������ڵ�ǰ�û����򷵻��û���List�е��±�
	 * ��������ڣ��򷵻�-1
	 */
	public boolean isExistUser(String username) {
		UserDao userDao=new UserDaoImpl();
		return userDao.isExistUser(username);
		
	}



	@Override
	public void regist(User user) {
		UserDao userDao=new UserDaoImpl();
		userDao.register(user);
	}

	@Override
	public void updateUser(User user) {
		UserDao userDao=new UserDaoImpl();
		userDao.updateUser(user);
		
	}

	@Override
	public User getUser(int id) {
		UserDao userDao=new UserDaoImpl();
		return userDao.getUser(id);
		
	}



}
