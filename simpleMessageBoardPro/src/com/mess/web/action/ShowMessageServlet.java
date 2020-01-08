package com.mess.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mess.domain.Message;
import com.mess.service.MessageService;
import com.mess.service.Impl.MessageServiceImpl;

@WebServlet("/showMessageServlet")
public class ShowMessageServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		int mid=Integer.parseInt(request.getParameter("mid"));
		MessageService msi=new MessageServiceImpl();
		Message mess= msi.getMessage(mid);
		request.setAttribute("message",mess);
		request.getRequestDispatcher("/editMessage.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
