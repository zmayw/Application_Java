package com.mess.web.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.mess.domain.Message;
import com.mess.domain.User;
import com.mess.service.MessageService;
import com.mess.service.Impl.MessageServiceImpl;

@WebServlet("/addMessageServlet")
public class AddMessageServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//接收数据
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		int cateId=Integer.parseInt(request.getParameter("category"));
		User user=(User)request.getSession().getAttribute("existUser");
		System.out.println("user id"+user);
		int userId=user.getId();
		//封装数据
		Message mb=new Message();
		mb.setTitle(title);
		mb.setContent(content);
		mb.setCateId(cateId);
		mb.setUserid(userId);
		//Date date=new Date();
		//Timestamp time=new Timestamp(date.getTime());
		//mb.setCreateTime(time);
		//mb.setModifyTime(time);
		//处理数据
		MessageService msi=new MessageServiceImpl();
		msi.addMessage(mb);
		response.sendRedirect(request.getContextPath() + "/messageServlet");
	}

	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
