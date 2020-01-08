package com.mess.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mess.domain.Message;
import com.mess.domain.PageBean;
import com.mess.domain.User;
import com.mess.service.MessageService;
import com.mess.service.Impl.MessageServiceImpl;

@WebServlet("/myMessageServlet")
public class MyMessageServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		//获取从前台传过来的页数
		int pageNum=0;
		if(request.getParameter("pageNum")==null) {
			pageNum=1;
		}else {
			pageNum=Integer.parseInt(request.getParameter("pageNum"));
		}
		User user=(User)request.getSession().getAttribute("existUser");
		int userId=user.getId();
		//信息列表页数据处理
		MessageService ms=new MessageServiceImpl();
		PageBean<Message> pageBean=new PageBean<Message>();
		pageBean=ms.findByPage(pageNum,userId);
		request.setAttribute("pageBean",pageBean);
		System.out.println("myMessageServlet,pageBean...:"+pageBean);
		request.getRequestDispatcher("/myMessageList.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
