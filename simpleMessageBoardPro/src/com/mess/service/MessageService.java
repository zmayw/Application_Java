package com.mess.service;

import java.util.List;

import com.mess.domain.Message;
import com.mess.domain.PageBean;

public interface MessageService {
	
	public List<Message> getMessageList() ;
	
	public List<Message> getMessageList(int userId) ;
	
	public void addMessage(Message mess);

	public PageBean<Message> findByPage(int page);
	
	public PageBean<Message> findByPage(int page,int userId);
	
}
