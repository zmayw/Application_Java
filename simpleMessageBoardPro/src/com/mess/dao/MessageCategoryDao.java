package com.mess.dao;

import java.util.List;

import com.mess.domain.MessageCategory;



public interface MessageCategoryDao {

	
	public void addCategory(MessageCategory mc);
	
	public List<MessageCategory> getCategoryList();
}
