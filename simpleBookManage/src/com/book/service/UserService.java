package com.book.service;

import java.util.ArrayList;
import java.util.List;

import com.book.domain.User;



public interface UserService {
	public static final List<Object> users=new ArrayList<Object>();
	

	public int regist(User user);
	
	public int login(String username,String password);
	
	public int isUserExist(String username);
	
}
