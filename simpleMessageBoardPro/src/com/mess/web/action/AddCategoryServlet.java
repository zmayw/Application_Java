package com.mess.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mess.domain.MessageCategory;
import com.mess.service.MessageCategoryService;
import com.mess.service.Impl.MessageCategoryServiceImpl;

@WebServlet("/addCategoryServlet")
public class AddCategoryServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//接收数据
		String name=request.getParameter("cname");
		String desc=request.getParameter("cdesc");
		//封装数据
		MessageCategory mc=new MessageCategory();
		mc.setCid(0);
		mc.setName(name);
		mc.setDesc(desc);
		//处理数据
		MessageCategoryService mcs = new MessageCategoryServiceImpl();		
		mcs.addCategory(mc);
		//response.sendRedirect(request.getContextPath() + "/messageCategoryList.jsp");
		response.sendRedirect(request.getContextPath() + "/categoryServlet");
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	
}
