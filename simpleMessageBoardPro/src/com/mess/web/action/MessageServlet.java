package com.mess.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.mess.domain.Message;
import com.mess.domain.PageBean;
import com.mess.service.MessageService;
import com.mess.service.Impl.MessageServiceImpl;

@WebServlet("/messageServlet")
public class MessageServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		/*MessageService ms=new MessageServiceImpl();
		List<Message> msList=new ArrayList<Message>();
		msList=ms.getMessageList();
		request.setAttribute("msList", msList);
		System.out.println("msList:"+msList);
		*/
		
		//获取从前台传过来的页数
		int pageNum=0;
		if(request.getParameter("pageNum")==null) {
			pageNum=1;
		}else {
			pageNum=Integer.parseInt(request.getParameter("pageNum"));
		}
		
		//信息列表页数据处理
		MessageService ms=new MessageServiceImpl();
		PageBean<Message> pageBean=new PageBean<Message>();
		pageBean=ms.findByPage(pageNum);
		request.setAttribute("pageBean",pageBean);
		System.out.println("messageServlet,pageBean...:"+pageBean);
		request.getRequestDispatcher("/messageList.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
