package com.mess.dao;

import java.util.List;

import com.mess.domain.Message;

public interface MessageDao {

	
	public List<Message> getMessageList();
	
	public List<Message> getMessageList(int userId);
	
	public void addMessage(Message mess);

	public int findRowsCount();

	public List<Message> getRowsList(int begin, int limit);

	public List<Message> getRowsList(int begin, int limit, int userId);

	int findRowsCount(int userId);
}
