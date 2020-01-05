package com.mess.service;

import java.util.List;

import com.mess.domain.MessageCategory;

public interface MessageCategoryService {
	
	public void addCategory(MessageCategory mc);
	
	public List<MessageCategory> getCategoryList();

}
