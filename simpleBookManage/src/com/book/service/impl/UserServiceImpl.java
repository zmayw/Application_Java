package com.book.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.book.domain.User;
import com.book.service.UserService;

public class UserServiceImpl implements UserService {
	private static final List<User> users=new ArrayList<User>();
	
	public List<User> getUsers() {
		return UserServiceImpl.users;
	}
	
	/*
	 * 该方法返回值n=-1时，说明该用户已存在
	 * n=0时，说明注册成功.
	 */
	public int regist(User user) {
		int n=isUserExist(user.getName());
		if(n<0) {
			users.add(user);
			n=0;
		}else {
			n=-1;
		}
		return n;
	}
	
	/* 返回值>=0，表示登录成功，且返回该用户在列表中的index
	 * 返回值n=-1说明，登录失败，该用户不存在。
	 * 返回值n=-2说明，登录失败，官码不正确
	 */
	public int login(String username,String password) {
		int n=isUserExist(username);
		if(n>=0) {
			if(users.get(n).getPassword().equals(password)) {
				return n;
			}else {
				return -2;
			}
		}else {
			return -1;
		}
		
	}
	
	/*
	 * 如果存在当前用户，则返回用户在List中的下标
	 * 如果不存在，则返回-1
	 */
	public int isUserExist(String username) {
		int n=-1;
		for(User user:users) {
			if(user.getName().equals(username)){
				n=users.indexOf(user);
				return n; 
			}
		}
		return n;
		
	}




}
