package com.mess.service.Impl;

import java.util.ArrayList;
import java.util.List;

import com.mess.dao.MessageCategoryDao;
import com.mess.dao.impl.MessageCategoryDaoImpl;
import com.mess.domain.MessageCategory;
import com.mess.service.MessageCategoryService;

public class MessageCategoryServiceImpl implements MessageCategoryService{

	@Override
	public void addCategory(MessageCategory mc) {
		MessageCategoryDao mcd=new MessageCategoryDaoImpl();
		mcd.addCategory(mc);
	}

	@Override
	public List<MessageCategory> getCategoryList() {
		MessageCategoryDao mcd=new MessageCategoryDaoImpl();
		return mcd.getCategoryList();
	}

}
