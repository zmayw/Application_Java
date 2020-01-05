package com.mess.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.mess.domain.Message;
import com.mess.domain.PageBean;
import com.mess.service.MessageService;
import com.mess.service.Impl.MessageServiceImpl;

@WebServlet("/messagePage")
public class MessagePageServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		int num=Integer.parseInt(request.getParameter("pageNum"));
		MessageService ms=new MessageServiceImpl();
		PageBean<Message> pageBean=new PageBean<Message>();
		pageBean=ms.findByPage(num);
		System.out.println("pageBean....:"+pageBean);
		List<Message> messList=new ArrayList<Message>();
		messList=pageBean.getList();
		System.out.println("pageBean.... message list:"+messList);
		String json=JSON.toJSONString(messList);
		response.getWriter().println(json);
	}
	


	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		doGet(request,response);
	}

}
