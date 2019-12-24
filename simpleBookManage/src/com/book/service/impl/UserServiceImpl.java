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
	 * �÷�������ֵn=-1ʱ��˵�����û��Ѵ���
	 * n=0ʱ��˵��ע��ɹ�.
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
	
	/* ����ֵ>=0����ʾ��¼�ɹ����ҷ��ظ��û����б��е�index
	 * ����ֵn=-1˵������¼ʧ�ܣ����û������ڡ�
	 * ����ֵn=-2˵������¼ʧ�ܣ����벻��ȷ
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
	 * ������ڵ�ǰ�û����򷵻��û���List�е��±�
	 * ��������ڣ��򷵻�-1
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
