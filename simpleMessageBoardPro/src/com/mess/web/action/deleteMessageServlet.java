package com.mess.web.action;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mess.domain.User;
import com.mess.service.MessageService;
import com.mess.service.Impl.MessageServiceImpl;

@WebServlet("/deleteMessageServlet")
public class deleteMessageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		int mid=Integer.parseInt(request.getParameter("mid"));
		User user=(User)request.getSession().getAttribute("existUser");
		MessageService msi=new MessageServiceImpl();
		msi.deleteMessage(mid,user.getId());
		response.sendRedirect(request.getContextPath()+"/myMessageServlet");
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		doGet(request,response);
	}
}
