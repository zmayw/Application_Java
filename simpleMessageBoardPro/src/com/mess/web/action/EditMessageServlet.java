package com.mess.web.action;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mess.domain.User;
import com.mess.service.MessageService;
import com.mess.service.Impl.MessageServiceImpl;

@WebServlet("/editMessageServlet")
public class EditMessageServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//接收数据
		int mid=Integer.parseInt(request.getParameter("mid"));
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		//处理数据
		MessageService msi=new MessageServiceImpl();
		User user=new User();
		user=(User) request.getSession().getAttribute("existUser");
		msi.updateMessage(mid,title, content,user.getId());
		response.sendRedirect(request.getContextPath()+"/myMessageServlet");
	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		doGet(request,response);
	}
}
