package com.mess.service.Impl;

import java.util.List;

import com.mess.dao.MessageDao;
import com.mess.dao.impl.MessageDaoImpl;
import com.mess.domain.Message;
import com.mess.domain.PageBean;
import com.mess.service.MessageService;

public class MessageServiceImpl implements MessageService {

	@Override
	public List<Message> getMessageList() {
		MessageDao md=new MessageDaoImpl();
		return md.getMessageList();
	}

	@Override
	public void addMessage(Message mess) {
		MessageDao md=new MessageDaoImpl();
		md.addMessage(mess);		
	}

	@Override
	public PageBean<Message> findByPage(int pageNum) {
		PageBean<Message> pageBean=new PageBean<Message>();
		//封装当前的页数
		pageBean.setPageNum(pageNum);
		//封装每页显示记录数
		int limit=2;
		pageBean.setLimitRows(limit);
		//封装总记录数
		MessageDao md=new MessageDaoImpl();
		int rowsCount=md.findRowsCount();
		pageBean.setRowsCount(rowsCount);
		//封装总页数,根据总记录数和每页记录数计算
		int pagesCount=0;
		
		if(rowsCount % limit == 0) {
			pagesCount=rowsCount / limit;
		}else {
			pagesCount=rowsCount / limit + 1;
		}
		pageBean.setPagesCount(pagesCount);
		int begin=(pageNum-1)*limit;
		System.out.println("md.getRowsList(begin, limit):"+md.getRowsList(begin, limit));
		pageBean.setList(md.getRowsList(begin, limit));
		
		return pageBean;
	}

	@Override
	public List<Message> getMessageList(int userId) {
		MessageDao md=new MessageDaoImpl();
		return md.getMessageList(userId);
	}

	@Override
	public PageBean<Message> findByPage(int pageNum, int userId) {
		PageBean<Message> pageBean=new PageBean<Message>();
		//封装当前的页数
		pageBean.setPageNum(pageNum);
		//封装每页显示记录数
		int limit=2;
		pageBean.setLimitRows(limit);
		//封装总记录数
		MessageDao md=new MessageDaoImpl();
		int rowsCount=md.findRowsCount(userId);
		pageBean.setRowsCount(rowsCount);
		System.out.println("PageBean findByPage userId:"+userId+"rowsCount:"+rowsCount);
		//封装总页数,根据总记录数和每页记录数计算
		int pagesCount=0;
		
		if(rowsCount % limit == 0) {
			pagesCount=rowsCount / limit;
		}else {
			pagesCount=rowsCount / limit + 1;
		}
		pageBean.setPagesCount(pagesCount);
		int begin=(pageNum-1)*limit;
		System.out.println("md.getRowsList(begin, limit,userId):"+md.getRowsList(begin, limit,userId));
		pageBean.setList(md.getRowsList(begin, limit,userId));
		
		return pageBean;
	}

	@Override
	public Message getMessage(int mid) {
		Message ms=new Message();
		MessageDao md=new MessageDaoImpl();
		ms=md.getMessage(mid);
		return ms;
	}

	@Override
	public void updateMessage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMessage(int mid, String title, String content, int userId) {
		MessageDao mdi=new MessageDaoImpl();
		mdi.updateMessage(mid,title,content,userId);
	}

	@Override
	public void deleteMessage(int mid, int userId) {
		MessageDao mdi=new MessageDaoImpl();
		mdi.deleteMessage(mid,userId);
		
	}
	
	

}
